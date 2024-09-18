public class Pet {
    private int id;
    private String name;
    private Category category;
    private List<Tag> tags;
    private String status;

    public Pet(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.category = builder.category;
        this.tags = builder.tags;
        this.status = builder.status;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Category getCategory() {
        return category;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public String getStatus() {
        return status;
    }

    public static class Builder {
        private int id;
        private String name;
        private Category category;
        private List<Tag> tags;
        private String status;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder setTags(List<Tag> tags) {
            this.tags = tags;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Pet build() {
            return new Pet(this);
        }
    }
}