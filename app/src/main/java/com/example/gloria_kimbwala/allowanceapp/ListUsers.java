package com.example.gloria_kimbwala.allowanceapp;

/**
 * Created by gloria_kimbwala on 2/24/18.
 */

public class ListUsers {

    private String head;
    private int total;

        public ListUsers(String head, int total) {
            this.head = head;
            this.total = total;
        }

    public String getHead() {
            return head;
        }

    public int getTotal() {
            return total;
        }
    }

