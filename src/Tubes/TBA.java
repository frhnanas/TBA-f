package Tubes;

import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import Tubes.View;
import java.util.*;
import java.util.Scanner;

public class TBA implements ActionListener{
    View view;
    private String[] subjek = {"kamu","saya","mahasiswa","dosen","petugas"};
    private String[] predikat = {"menjaga","melestarikan","bermain","bekerja","belajar"};
    private String[] objek = {"laporan","proyek","tanaman","laptop","gitar"};
    private String[] keterangan = {"kemarin","besok","di perpustakaan","di laboratorium","di kelas"};  
    private char[] SPOK;
    private int kalLength;
    private int S;
    private int P;
    private int O;
    private int K;
    
    public TBA() {
        
        view = new View();
        view.addActionListener(this);
        view.setVisible(true);        
    }

    public String[] getSubjek() {
        return subjek;
    }

    public String[] getPredikat() {
        return predikat;
    }

    public String[] getObjek() {
        return objek;
    }

    public String[] getKeterangan() {
        return keterangan;
    }
    
    
    
    public void parsing(){
        char[] kalCH = view.getTfKalimatParser().toLowerCase().toCharArray();
        boolean cek1 = false;
        boolean sub = false;
        boolean pre = false;
        boolean obj = false;
        boolean ket = false;
        
        try{
            
            if(!sub && !cek1){
                
                for (int i = 0; i < 5; i++){                    
                    kalLength = 0;
                    SPOK = subjek[i].toCharArray();
                    for (int j = 0; j < SPOK.length; j++) {

                        if(SPOK[j] == kalCH[kalLength]){
                            kalLength++;
                            S = kalLength;
                        }else{
                            j = 100;                            
                        }
                    }
                    
                    if(kalCH[kalLength] == ' '){                        
                        sub = true;
                        i = 100;
                    }else if (kalCH[kalLength] == '.'){
                        sub = true;
                    }
                }
            }
            
            if(kalCH[kalLength] == ' ' && sub && kalCH[kalLength+1] != '.'){
                
                for (int i = 0; i < 5; i++) {                    
                    kalLength = S+1;
                    SPOK = predikat[i].toCharArray();
                    
                    for (int j = 0; j < SPOK.length; j++) {                        
                        if(SPOK[j] == kalCH[kalLength]){
                            kalLength++;
                            P = kalLength;
                        }else{
                            j = 100;                            
                        }
                    }      
                    
                    if(kalCH[kalLength] == ' ' && !pre){
                        i = 100;
                        pre = true;
                    }else if(kalCH[kalLength] == '.' && !pre){
                        i = 100;
                        pre = true;
                        cek1 = true;
                    }
                }
                
            }
            if(kalCH[kalLength] == ' ' && !cek1 && pre && !ket && kalCH[kalLength+1] != '.'){
                
                for (int i = 0; i < 5; i++) {
                    kalLength = P+1;
                    SPOK = keterangan[i].toCharArray();
                    for (int j = 0; j < SPOK.length; j++) {
                        
                        if(SPOK[j] == kalCH[kalLength]){
                            kalLength++;
                            K = kalLength;                            
                        }else{
                            j = 100;
                        }
                    }
                    
                    if(kalCH[kalLength] == '.' && !ket){
                        i = 100;
                        cek1 = true;
                        ket = true;
                        
                    }else if (kalCH[kalLength] == ' ' && !ket){
                        i = 100;
                        cek1 = false;
                        ket = false;
                    }
                }
                
                if (!ket){
                    
                    kalLength = P;
                }
            }
            
            if(!ket && kalCH[kalLength] == ' ' && kalCH[kalLength+1] != '.' && !cek1 && pre){
                
                for (int i = 0; i < 5; i++){
                    kalLength = P+1;
                    SPOK = objek[i].toCharArray();
                    
                    for (int j = 0; j < SPOK.length; j++) {                        
                        if(SPOK[j] == kalCH[kalLength]){
                            kalLength++;
                            O = kalLength;
                        }else{
                            j = 100;                            
                        }
                    }
                    
                    if(kalCH[kalLength] == ' ' && !obj){                         
                        i = 100;
                        obj = true;
                    }else if(kalCH[kalLength] == '.'&& !obj){
                        i = 100;
                        cek1 = true;
                        obj = true;
                    }
                }
                if(!obj){
                    
                }else if(kalCH[kalLength] == ' ' && !cek1 && obj && kalCH[kalLength+1] != '.'){
                    
                    for (int i = 0; i < 5; i++) {
                        kalLength = O+1;
                        SPOK = keterangan[i].toCharArray();
                        
                        for (int j = 0; j < SPOK.length; j++) {
                            if(SPOK[j] == kalCH[kalLength]){
                                kalLength++;
                                K = kalLength;
                            }else{
                                j = 100;                                
                            }
                        }
                        if(kalCH[kalLength] == '.' && !ket){
                            i = 100;
                            cek1 = true;
                            ket = true;
                        }
                    }
                    
                }
                
            }
        }
        catch(Exception e){
            
        }
        
        if(cek1){
            view.setTfStatusParser("Kalimat Valid");
            if (sub) {
                if(pre){
                    view.setTfStatusParser("Kalimat S ~ P Valid");
                    if (ket) {
                        view.setTfStatusParser("Kalimat S ~ P ~ K Valid");
                        if (obj) {
                            view.setTfStatusParser("Kalimat S ~ P ~ O ~ K Valid");
                        }
                    }else{
                        if (obj) {
                            view.setTfStatusParser("Kalimat S ~ P ~ O Valid");
                        }
                    }
                }
                
            }
        }else{
            view.setTfStatusParser("Kalimat Tidak Valid");
        }
        
        if(sub){
            view.setTfSub1("Valid");
        }else{
            view.setTfSub1("");
        }
        
        if(pre){
            view.setTfPre1("Valid");
        }else{
            view.setTfPre1("");
        }
        
        if(obj){
            view.setTfObj1("Valid");
        }else{
            view.setTfObj1("");
        }
        
        if(ket){
            view.setTfKet1("Valid");
        }else{
            view.setTfKet1("");
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source.equals(view.getBtnCek())){
            parsing();
        }
    }
    
}