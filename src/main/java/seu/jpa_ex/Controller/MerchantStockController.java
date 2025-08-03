package seu.jpa_ex.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import seu.jpa_ex.Api.ApiResponse;
import seu.jpa_ex.Model.MerchantStockModel;
import seu.jpa_ex.Service.MerchantStockService;

import java.util.Objects;

@RestController
@RequestMapping("/api/v1/merchantStock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantStockService;


    @GetMapping("/get")
    public ResponseEntity<?> getMerchantStockModels() {
        return ResponseEntity.status(200).body(merchantStockService.GetAllMerchantStock());
    }

    @PostMapping("/add")
    public ResponseEntity<?> addMerchantStockModel(@Valid @RequestBody MerchantStockModel merchantStockModel , Errors errors){
        if(errors.hasErrors()){
            return ResponseEntity.status(400).body(new ApiResponse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        boolean isAdded = merchantStockService.addMerchantStock(merchantStockModel);
        if(isAdded){
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Added Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock Not Added The MerchantID not Exist or productId not Exist"));

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateMerchantStockModel(@PathVariable Integer id, @Valid @RequestBody MerchantStockModel merchantStockModel, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(400).body(new ApiResponse(Objects.requireNonNull(errors.getFieldError()).getDefaultMessage()));
        }
        boolean isUpdated = merchantStockService.updateMerchantStock(id, merchantStockModel);
        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Updated"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock Not Updated"));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteMerchantStockModel(@PathVariable Integer id) {
        boolean isDeleted = merchantStockService.deleteMerchantStock(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiResponse("Merchant Stock Deleted"));
        }
        return ResponseEntity.status(400).body(new ApiResponse("Merchant Stock Not Deleted"));
    }


    @PutMapping("/add-stock/{merchantId}/{productId}/{quantity}")
    public ResponseEntity<?> addStock( @PathVariable Integer merchantId, @PathVariable Integer productId, @PathVariable int quantity){
        String response = merchantStockService.addStock( merchantId, productId, quantity);
        if (response.equals("Stock added successfully")){
            return ResponseEntity.status(200).body(new ApiResponse("Stock Added Successfully"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(response));
    }


    @PutMapping("/buy-product/{userid}/{productid}/{merchantid}")
    public ResponseEntity<?> buyProduct(@PathVariable Integer userid,@PathVariable Integer productid,@PathVariable Integer merchantid){
        String response = merchantStockService.buyProduct(userid,productid,merchantid);
        if(response.equals("Purchase successful")){
            return ResponseEntity.status(200).body(new ApiResponse("Purchase successful"));
        }
        return ResponseEntity.status(400).body(new ApiResponse(response));
    }

}
