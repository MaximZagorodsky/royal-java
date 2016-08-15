package com.transport.util_entity;

import com.transport.model.Address;
import lombok.Data;

import java.util.List;

/**
 * Created by Proxima on 14.08.2016.
 */
@Data
public class SearchDistance {
    private List<Address> loadingAddress;
    private List<Address> unloadingAddress;
}
