package com.library.management.model;

public class BookBranch {
    private final String id;
    private final String bookId;
    private final String branchId;
    private int copiesTotal;
    private int copiesAvailable;

    public BookBranch(String id, String bookId, String branchId, int copiesTotal) {
        this.id = id;
        this.bookId = bookId;
        this.branchId = branchId;
        this.copiesTotal = copiesTotal;
        this.copiesAvailable = copiesTotal;
    }
    public String getId() {return id;}
    public String getBookId() {return bookId;}
    public String getBranchId() {return branchId;}
    public int getCopiesTotal() {return copiesTotal;}
    public int getCopiesAvailable() {return copiesAvailable;}

    public void addCopies(int n){
        copiesTotal += n;
        copiesAvailable -= n;
    }
    public boolean checkoutCopy(){
        if(copiesAvailable <= 0)return false;copiesAvailable --;return true;
    }
    public void returnCopies() {
        copiesAvailable++;
    }

    @Override
    public String toString(){
        return String.format("%s |Book-Id: %s |Branch-Id: %s|Total: %s|Books_Available: %s|",id,bookId,branchId,copiesTotal,copiesAvailable);
    }

}
