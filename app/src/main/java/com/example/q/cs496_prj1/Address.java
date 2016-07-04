package com.example.q.cs496_prj1;

public class Address {
    public String name;
    public String addr;

    String get_name() {
        return this.name;
    }

    String get_addr() {
        return this.addr;
    }

    Address (String name, String addr) {
        this.name = name;
        this.addr = addr;
    }

}
