/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import entity.grafo.Grafo;

public class InputInstance {
    //leitura do grafo
	
	public static Grafo getInstance(String file) {
		//cria um grafo
		Grafo grafo = new Grafo();
		InputStream is;
		InputStreamReader isr;
		BufferedReader br;
		
		try {
			//lé arquivos
			is = new FileInputStream(file);
            //comversor de caracter
			isr = new InputStreamReader(is, StandardCharsets.UTF_8);
            //variavel que via guardar a linha
			br = new BufferedReader(isr);
            //cria as linhas em string
			String line;
			while( ( line = br.readLine() ) !=null) {//enquanto a linha for diferente de vasio
            //isso é o numero de lugares, isso é as 2 ilhas e as 2 margens
				int numeroVertices = Integer.parseInt(line);
				for(int i=0; i<numeroVertices; i++) {
                    //le o nome das coisas 
					line = br.readLine();
					String nomeVertice = line.trim();
					grafo.addVertice(nomeVertice);
				}
                //para ler as pontes
				line = br.readLine();
				int numeroArestas = Integer.parseInt(line);
				for(int i=0; i<numeroArestas; i++) {
					line = br.readLine();
					String[] nomes = line.split(" ");
                    //desfine a onde as pontes se ligam
					String nomeAresta = nomes[0];
					String nomeV1 = nomes[1];
					String nomeV2 = nomes[2];
					grafo.addAresta(nomeAresta, nomeV1, nomeV2);
				}
				
			}
			
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return grafo;
		
		
	}

}
