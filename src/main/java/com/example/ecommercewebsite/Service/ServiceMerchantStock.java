package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.MerchantStock;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiceMerchantStock {

    ArrayList<MerchantStock> merchantStocks = new ArrayList<>();

    public ArrayList<MerchantStock> getMerchantStocks() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock)
    {
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(String id,MerchantStock merchantStock)
    {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if (merchantStocks.get(i).getId().equals(id))
            {
                merchantStocks.set(i,merchantStock);
                return true;
            }
        }
        return false;
    }

    public boolean deleteMerchantStock(String id)
    {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getId().equals(id))
            {
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;
    }

    public boolean changeStock(int stock, char c, String productID, String merchantID)
    {
        for (int i = 0; i < merchantStocks.size(); i++) {
            if(merchantStocks.get(i).getProductID().equals(productID) && merchantStocks.get(i).getMerchantID().equals(merchantID))
            {
                if(c == 'r' || c == 'R') {
                    merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() + stock);
                    return true;
                }
                if(c == 'l' || c == 'L') {
                    merchantStocks.get(i).setStock(merchantStocks.get(i).getStock() - stock);
                    return true;
                }

            }
        }
        return false;
    }
}
