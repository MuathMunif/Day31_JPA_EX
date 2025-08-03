package seu.jpa_ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seu.jpa_ex.Model.MerchantModel;
import seu.jpa_ex.Model.MerchantStockModel;
import seu.jpa_ex.Model.ProductModel;
import seu.jpa_ex.Model.UserModel;
import seu.jpa_ex.Repository.MerchantRepository;
import seu.jpa_ex.Repository.MerchantStockRepository;
import seu.jpa_ex.Repository.ProductRepository;
import seu.jpa_ex.Repository.UserRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantStockService {
    private final MerchantStockRepository merchantStockRepository;
    private final MerchantRepository merchantRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<MerchantStockModel> GetAllMerchantStock(){
        return merchantStockRepository.findAll();
    }

    //todo there is error here the condition is always 'false'
    public boolean addMerchantStock(MerchantStockModel merchantStockModel){
        MerchantModel merchantModel = merchantRepository.getById(merchantStockModel.getMerchantId());
        ProductModel productModel = productRepository.getById(merchantStockModel.getProductId());
        if(productModel == null || merchantModel == null){
            return false;
        }
        merchantStockRepository.save(merchantStockModel);
        return true;
    }

    public boolean updateMerchantStock(Integer id,MerchantStockModel merchantStockModel){
        MerchantStockModel merchantStock = merchantStockRepository.getById(id);
        if(merchantStock == null){
            return false;
        }
        merchantStockModel.setMerchantId(merchantStock.getMerchantId());
        merchantStockModel.setProductId(merchantStockModel.getProductId());
        merchantStockModel.setStock(merchantStockModel.getStock());
        merchantStockRepository.save(merchantStockModel);
        return true;
    }


    public boolean deleteMerchantStock(Integer id){
        MerchantStockModel merchantStockModel = merchantStockRepository.getById(id);
        if(merchantStockModel == null){
            return false;
        }
        merchantStockRepository.deleteById(id);
        return true;
    }

    public String addStock(Integer merchantId, Integer productId, int quantity){
        MerchantModel merchantModel = merchantRepository.getById(merchantId);
        ProductModel productModel = productRepository.getById(productId);
        List<MerchantStockModel> merchantStockList = merchantStockRepository.findAll();
        if(productModel == null){
            return "The product does not exist";
        }
        if (merchantModel == null){
            return "The merchant does not exist";
        }
        for (MerchantStockModel stock : merchantStockList) {
            if (stock.getMerchantId().equals(merchantId) && stock.getProductId().equals(productId)) {
                int newStock = stock.getStock() + quantity;
                stock.setStock(newStock);
                merchantStockRepository.save(stock);
                return "Stock added successfully";
            }
        }
        return "The Stock entry does not exist";

    }

    public String buyProduct(Integer userId , Integer productId, Integer merchantId){
        UserModel userModel = userRepository.getById(userId);
        ProductModel productModel = productRepository.getById(productId);
        MerchantModel merchantModel = merchantRepository.getById(merchantId);
        if(userModel == null){
            return "The user does not exist";
        }
        if(productModel == null){
            return "The product does not exist";
        }
        if(merchantModel == null){
            return "The merchant does not exist";
        }

        List<MerchantStockModel> merchantStockList = merchantStockRepository.findAll();

        for (MerchantStockModel stock : merchantStockList) {
            if (stock.getMerchantId().equals(merchantId) && stock.getProductId().equals(productId)) {
                if (stock.getStock() <= 0) {
                    return "Product is out of stock";
                }

                if (userModel.getBalance() < productModel.getPrice()) {
                    return "User balance is not enough";
                }

                userModel.setBalance(userModel.getBalance() - productModel.getPrice());
                stock.setStock(stock.getStock() - 1);

                userRepository.save(userModel);
                merchantStockRepository.save(stock);

                return "Purchase done successfully";
            }
        }

        return "The stock entry does not exist";
    }
}
