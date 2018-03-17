package com.company;

import java.io.IOException;

public class Main {


    public static void main(String[] args) {

        TwitterAPISearch twitterAPISearch = new TwitterAPISearch();
        try {
            System.out.println(twitterAPISearch.search(null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}