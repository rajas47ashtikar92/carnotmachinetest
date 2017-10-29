package com.developer.r47.carnotmachinetest.configs;

import com.developer.r47.carnotmachinetest.models.CarnotMachineTestError;

/**
 * Created by r47 on 29/10/17.
 */

public interface CarnotMachineTestCallback<T> {
    void onSuccess(T response);
    void onError(CarnotMachineTestError error);
}
