package com.company;

import com.company.Entity.User;

import java.sql.Statement;
import java.util.List;


public class Main {


    public static void main(String[] args) {

        UserRepository.delete(4);

        System.out.println(UserRepository.get());


    }
}

