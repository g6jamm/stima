package com.g6jamm.stima.domain.model;

public class ResourceType {

    private final String NAME;
    private final int PRICE_PR_HOUR;
    private int ID;

    public ResourceType(ResourceTypeBuilder resourceTypeBuilder) {
        this.ID = resourceTypeBuilder.id;
        this.NAME = resourceTypeBuilder.name;
        this.PRICE_PR_HOUR = resourceTypeBuilder.pricePrHour;
    }

    public int getId() {
        return ID;
    }

    public int getPricePrHour() {
        return PRICE_PR_HOUR;
    }

    public String getName() {
        return NAME;
    }

    public static class ResourceTypeBuilder {
        private String name;
        private int pricePrHour;
        private int id;

        public ResourceTypeBuilder name(String name) {
            this.name = name;
            return this;
        }

        public ResourceTypeBuilder pricePrHour(int pricePrHour) {
            this.pricePrHour = pricePrHour;
            return this;
        }

        public ResourceTypeBuilder id(int id) {
            this.id = id;
            return this;
        }

        public void reset() {
            this.name = null;
            this.id = 0;
            this.pricePrHour = 0;
        }

        public ResourceType build() {
            ResourceType resourceType = new ResourceType(this);
            reset();
            return resourceType;
        }


    }
}
