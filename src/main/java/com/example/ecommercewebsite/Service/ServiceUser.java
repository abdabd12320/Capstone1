package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@RequiredArgsConstructor
public class ServiceUser {

    ArrayList<User> users = new ArrayList<>();

    private final ServiceMerchantStock serviceMerchantStock;
    private final ServiceProduct serviceProduct;

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user)
    {
        users.add(user);
    }

    public boolean updateUser(String id, User user)
    {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id))
            {
                users.set(i,user);
                return true;
            }
        }
        return false;
    }

    public boolean deleteUser(String id)
    {
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(id))
            {
                users.remove(i);
                return true;
            }
        }
        return false;
    }

    public String buyProduct(String userID,String productID,String merchantID)
    {
        int check1 = -1,check2 = -1,check3 = -1;
        for (int i = 0; i < serviceMerchantStock.getMerchantStocks().size(); i++) {
            if(serviceMerchantStock.getMerchantStocks().get(i).getMerchantID().equals(merchantID)&&serviceMerchantStock.getMerchantStocks().get(i).getProductID().equals(productID))
            {
                check1 = i;
                break;
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(userID) && users.get(i).getRole().equalsIgnoreCase("Customer"))
            {
                check2 = i;
                break;
            }
        }
        if(check1 == -1 || check2 == -1)
        {
            return "";
        }
        for (int i = 0; i < serviceProduct.getProducts().size(); i++) {
            if (serviceMerchantStock.getMerchantStocks().get(check1).getProductID().equals(serviceProduct.getProducts().get(i).getId()))
            {
                check3 = i;
                break;
            }
        }
        if (serviceMerchantStock.getMerchantStocks().get(check1).getStock() > 0 && users.get(check2).getBalance() >= serviceProduct.getProducts().get(check3).getPrice())
        {
            serviceMerchantStock.getMerchantStocks().get(check1).setStock(serviceMerchantStock.getMerchantStocks().get(check1).getStock() - 1);
            users.get(check2).setBalance(users.get(check2).getBalance() - serviceProduct.getProducts().get(check3).getPrice());
            return "true";
        }
        return "false";
    }

    public boolean productReturn(String userID,String productID,String merchantID)
    {
        int check1 = -1,check2 = -1,check3 = -1;
        for (int i = 0; i < serviceMerchantStock.getMerchantStocks().size(); i++) {
            if(serviceMerchantStock.getMerchantStocks().get(i).getMerchantID().equals(merchantID)
                    &&serviceMerchantStock.getMerchantStocks().get(i).getProductID().equals(productID))
            {
                check1 = i;
                break;
            }
        }
        for (int i = 0; i < users.size(); i++) {
            if(users.get(i).getId().equals(userID) && users.get(i).getRole().equalsIgnoreCase("Customer"))
            {
                check2 = i;
                break;
            }
        }
        for (int i = 0; i < serviceProduct.getProducts().size(); i++) {
            if (serviceMerchantStock.getMerchantStocks().get(check1).getProductID().equals(serviceProduct.getProducts().get(i).getId()))
            {
                check3 = i;
                break;
            }
        }
        if (check1 != -1 && check2 != -1)
        {
            serviceMerchantStock.getMerchantStocks().get(check1).setStock(serviceMerchantStock.getMerchantStocks().get(check1).getStock() + 1);
            users.get(check2).setBalance(users.get(check2).getBalance() + serviceProduct.getProducts().get(check3).getPrice());
            return true;
        }
        return false;
    }

    //    Raise the price
//    Lower the price
    public String changePriceByMerchant(double amount,char pr,String mID,String pID,String uID)
    {
        int c1 = -1,c2 = -1;
        boolean b = false;
        for (int i = 0; i < serviceMerchantStock.getMerchantStocks().size(); i++) {
            if(serviceMerchantStock.getMerchantStocks().get(i).getMerchantID().equals(mID))
            {
                c1 = i;
            }
        }
        for (int i = 0; i < getUsers().size(); i++) {
            if(getUsers().get(i).getId().equals(uID) && getUsers().get(i).getRole().equalsIgnoreCase("Admin"))
            {
                b = true;
            }
        }
        for (int i = 0; i < serviceProduct.getProducts().size(); i++) {
            if(serviceProduct.getProducts().get(i).getId().equals(pID)) {c2 = i;}
        }
        if(c1 == -1 || c2 == -1) {return "";}
        if (!b) {return "admin";}
        if(pr == 'l' || pr == 'L')
        {
            serviceProduct.getProducts().get(c2).setPrice(serviceProduct.getProducts().get(c2).getPrice() - amount);
            return "true";
        }
        if(pr == 'r'  || pr == 'R')
        {
            serviceProduct.getProducts().get(c2).setPrice(serviceProduct.getProducts().get(c2).getPrice() + amount);
            return "true";
        }
        return "false";
    }
}
