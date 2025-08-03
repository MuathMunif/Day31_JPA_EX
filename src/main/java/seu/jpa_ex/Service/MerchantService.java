package seu.jpa_ex.Service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import seu.jpa_ex.Model.MerchantModel;
import seu.jpa_ex.Repository.MerchantRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MerchantService {

    private final MerchantRepository merchantRepository;

    public List<MerchantModel> getAllMerchant(){
        return merchantRepository.findAll();
    }

    public void addMerchant(MerchantModel merchantModel){
        merchantRepository.save(merchantModel);
    }

    public boolean updateMerchant(Integer id,MerchantModel merchantModel){
        MerchantModel merchant = merchantRepository.getById(id);
        if (merchant == null){
            return false;
        }
        merchant.setName(merchantModel.getName());
        merchantRepository.save(merchant);
        return true;
    }

    public boolean deleteMerchant(Integer id){
        MerchantModel merchant = merchantRepository.getById(id);
        if (merchant == null){
            return false;
        }
        merchantRepository.delete(merchant);
        return true;
    }

}
