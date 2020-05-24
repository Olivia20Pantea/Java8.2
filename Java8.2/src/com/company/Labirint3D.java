package com.company;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Labirint3D {

    FileReader fin = new FileReader("lab.txt");
    Scanner inFile = new Scanner(fin);
    FileWriter fout = new FileWriter("drumoptim.txt");
    PrintWriter outFile = new  PrintWriter(fout);
    int xi,yi,zi;
    Object[] d=new Object[100];
    Object[] sol=new Object[100];
    int[][][] a=new int[100][100][100];
    int k,n,m,q,x,y,z,xf,yf,zf,nr_sol,dmin=Integer.MAX_VALUE;

    public Labirint3D() throws IOException {
    }

    public void labirint3D() throws IOException {

        n=inFile.nextInt();
        m=inFile.nextInt();
        q=inFile.nextInt();
        x=inFile.nextInt();
        y=inFile.nextInt();
        z=inFile.nextInt();
        xf=inFile.nextInt();
        yf=inFile.nextInt();
        zf=inFile.nextInt();
        for(int i=0;i<n;i++)
            for(int j=0;j<m;j++)
                for(int l=0;l<q;l++)
                    a[i][j][l]=inFile.nextInt();
        afis_mat(n,m,q,a);
        ies(x,y,z);
        outFile.println("Drumul care are lungime egala cu "+dmin+" este cel mai scurt drum de iesire din labirint: ");
        for(int i=0;i<dmin;i++)
            outFile.print("("+((Labirint3D)sol[i]).xi+','+((Labirint3D)sol[i]).yi+','+((Labirint3D)sol[i]).zi+") ");
        inFile.close();
        fout.close();
    }

    void afis_mat(int n,int m, int q, int[][][] a) //afiseaza matricea
    {
        for(int i=0;i<m;i++)
        {for(int j=0;j<n;j++)
        {for(int l=0;l<q;l++)
            outFile.print(a[i][j][l]+" ");
            outFile.println();}
            outFile.println();}
        outFile.println();
    }
    void tipar(int k) throws IOException //tipareste vectorul drum
    {
        nr_sol++;
        outFile.print("solutia "+nr_sol+"\n");
        if(k<dmin)
        {
            dmin=k;
            for(int i=0;i<dmin;i++)
            {
                sol[i]= new Labirint3D();
                ((Labirint3D)sol[i]).xi=((Labirint3D)d[i]).xi;
                ((Labirint3D)sol[i]).yi=((Labirint3D)d[i]).yi;
                ((Labirint3D)sol[i]).zi=((Labirint3D)d[i]).zi;
            }
        }
        for(int i=0;i<k;i++)
            outFile.print("("+((Labirint3D)d[i]).xi+','+((Labirint3D)d[i]).yi+','+((Labirint3D)d[i]).zi+") ");
        outFile.println();
        afis_mat(n,m,q,a);
    }

    void ies(int x,int y,int z) throws IOException //genereaza drumul
    {
        if(a[x][y][z]==0)
        {
            d[k]= new Labirint3D();
            a[x][y][z]=2;
            ((Labirint3D)d[k]).xi=x;
            ((Labirint3D)d[k]).yi=y;
            ((Labirint3D)d[k]).zi=z;
            k++;
            if(x==xf&&y==yf&&z==zf)
                tipar(k);
            else {
                if(x!=0&&y!=0&&z!=0&&x!=m&&y!=n&&z!=q){
                    ies(x + 1, y, z);
                    ies(x, y - 1, z);
                    ies(x, y + 1, z);
                    ies(1, y, z - 1);
                    ies(x - 1, y, z);
                    ies(1, y, z + 1);
                }
            }
            a[x][y][z]=0; //la revenire din apel demarchez celula pentru a o putea accesa si in cadrul altei prelucrari
            k--;//eliberez componenta din vectorul drumului
        }
    }
}
