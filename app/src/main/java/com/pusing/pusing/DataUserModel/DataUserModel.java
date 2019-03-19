package com.pusing.pusing.DataUserModel;

/**
 * Created by Abraham_Lundy on 21/03/2018.
 */

public class DataUserModel {

    String id;
    String nama;
    String saldo;

    public String getSaldo() {
        return saldo;
    }

    public void setKartu(String kartu) {
        this.saldo = saldo;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public DataUserModel(String id, String nama, String saldo) {
        this.id = id;
        this.nama = nama;
        this.saldo= saldo;
    }


}
