package com.example.ecommercewebsite.Service;

import com.example.ecommercewebsite.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class ServiceProduct {

    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product)
    {
        products.add(product);
    }

    public boolean updateProduct(String id,Product product)
    {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id))
            {
                products.set(i,product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id)
    {
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getId().equals(id))
            {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public Product searchByProduct(String name)
    {
        for (int i = 0; i < products.size(); i++) {
            if(products.get(i).getName().equals(name))
            {
                return products.get(i);
            }
        }
        return null;
    }

    public ArrayList<Product> getProductsLessPriceSorted()
    {
        ArrayList<Product> products1 = new ArrayList<>(products);
        for (int i = 0; i < products.size(); i++) {
            for (int j = i; j < products.size(); j++) {
                if(products1.get(i).getPrice() > products1.get(j).getPrice())
                {
                    Product temp = products1.get(i);
                    products1.set(i,products1.get(j));
                    products1.set(j,temp);
                }
            }
        }
        return products1;
    }
}
