package com.g6jamm.stima.data.repository.stub;

import com.g6jamm.stima.data.repository.ResourceTypeRepository;
import com.g6jamm.stima.domain.model.ResourceType;

import java.util.ArrayList;
import java.util.List;

public class ResourceTypeStub implements ResourceTypeRepository {

    private static List<ResourceType> resourceTypes = new ArrayList();


    public ResourceTypeStub() {
        if (resourceTypes.isEmpty()) {
            resourceTypes.add(
                    new ResourceType.ResourceTypeBuilder()
                            .name("Senior Developer")
                            .id(resourceTypes.size() + 1)
                            .pricePrHour(1250)
                            .build());

            resourceTypes.add(
                    new ResourceType.ResourceTypeBuilder()
                            .name("Project Manager")
                            .id(resourceTypes.size() + 1)
                            .pricePrHour(1000)
                            .build());
            resourceTypes.add(
                    new ResourceType.ResourceTypeBuilder()
                            .name("Junior Developer")
                            .id(resourceTypes.size() + 1)
                            .pricePrHour(800)
                            .build());
        }
    }

    public List<ResourceType> getResourceTypes() {
        return resourceTypes;
    }
}
