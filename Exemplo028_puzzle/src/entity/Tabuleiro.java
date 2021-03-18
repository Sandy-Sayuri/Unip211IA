package entity;

import java.util.Arrays;

public class Tabuleiro {
	//aqui é definido o tamanho da matriz
	public static final int N = 3; 
	//gera a matriz
	int[][] pecas;
	//emcapsulamento da matriz
	public Tabuleiro(int[][] aPecas) {
		this.pecas = aPecas;
	}
	
	public Posicao getPosicaoZero() {
		for(int i=0; i<Tabuleiro.N; i++) {
			for(int j=0; j<Tabuleiro.N; j++) {
				if(this.pecas[i][j]==0) {
					return new Posicao(i, j);
				}
			}
		}
		return null;
	}
	
	public boolean isAcaoValida(Acao acao) {
		Posicao p = getPosicaoZero();
		switch (acao) {
		case CIMA:     if (p.linha  == 0)               return false;
		case DIREITA:  if (p.coluna == Tabuleiro.N - 1) return false;
		case BAIXO:    if (p.linha  == Tabuleiro.N - 1) return false;
		case ESQUERDA: if (p.coluna == 0)               return false;
		default:
			return true;
		}
	}
	
	public void fazerAcao(Acao acao) {
		Posicao p0 = getPosicaoZero();
		Posicao posicaoPeca = null;
		switch (acao) {
		case CIMA:     posicaoPeca = new Posicao(p0.linha-1, p0.coluna  ); break;
		case DIREITA:  posicaoPeca = new Posicao(p0.linha  , p0.coluna+1); break;
		case BAIXO:    posicaoPeca = new Posicao(p0.linha+1, p0.coluna  ); break;
		case ESQUERDA: posicaoPeca = new Posicao(p0.linha  , p0.coluna-1); break;
		default:
			break;
		}
		int peca = this.pecas[posicaoPeca.linha][posicaoPeca.coluna];
		this.pecas[p0.linha][p0.coluna] = peca;
		this.pecas[posicaoPeca.linha][posicaoPeca.coluna] = 0;
	}
	
	public Tabuleiro deepCopy() {
		int[][] novoPecas = new int[Tabuleiro.N][Tabuleiro.N];
		for(int i=0; i<Tabuleiro.N; i++) {
			for(int j=0; j<Tabuleiro.N; j++) {
				novoPecas[i][j] = this.pecas[i][j];
			}
		}
		return new Tabuleiro(novoPecas);
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.deepHashCode(pecas);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Tabuleiro other = (Tabuleiro) obj;
		if (!Arrays.deepEquals(pecas, other.pecas))
			return false;
		return true;
	}



	@Override
	//Trasforma a lista em uma matriz
	public String toString() {
		String res = "";
		
		for(int i=0; i<Tabuleiro.N; i++) {
			for(int j=0; j<Tabuleiro.N; j++) {
				res += this.pecas[i][j] + " ";
			}
			res += "\n";
		}
		return res;
	}
	
	

}
