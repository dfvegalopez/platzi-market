package com.platzi.market.domain.services;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.Purchase;
import com.platzi.market.domain.repository.PurchaseRepostitory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PurchaseService {
    @Autowired
    private PurchaseRepostitory purchaseRepostitory;

    public List<Purchase> getAll() {
        return purchaseRepostitory.getAll();
    }



    public Optional<List<Purchase>> getByClient(String clientId) {
        return  purchaseRepostitory.getByClient(clientId);
    }

    public Purchase save(Purchase purchase) {
        return purchaseRepostitory.save(purchase);
    }


}
