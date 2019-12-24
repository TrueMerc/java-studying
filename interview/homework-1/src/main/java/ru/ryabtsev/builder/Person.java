package ru.ryabtsev.builder;

import lombok.Getter;

@Getter
public class Person {

    private String firstName;
    private String lastName;
    private String middleName;
    private String country;
    private String address;
    private String phone;
    private int age;
    private String gender;

    public class Builder {
        private Builder() {}

        public Builder addFirstName(String firstName) {
            Person.this.firstName = firstName;
            return this;
        }

        public Builder addLastName(String lastName) {
            Person.this.lastName = lastName;
            return this;
        }

        public Builder addMiddleName(String middleName) {
            Person.this.middleName = middleName;
            return this;
        }

        public Builder addCountry(String country) {
            Person.this.country = country;
            return this;
        }

        public Builder addAddress(String address) {
            Person.this.address = address;
            return this;
        }

        public Builder addPhone(String phone) {
            Person.this.phone = phone;
            return this;
        }

        public Builder addAge(int age) {
            Person.this.age = age;
            return this;
        }

        public Builder addGender(String gender) {
            Person.this.gender = gender;
            return this;
        }

        public Person build() {
            return Person.this;
        }
    }

    public static Builder getBuilder() {
        return new Person().new Builder();
    }
}
