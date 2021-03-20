package entity;

import java.util.ArrayList;
import java.util.Collection;

public class Estado {
	
	Tabuleiro tabuleiro;
	//encapsulament do tabuleiro
	public Estado(Tabuleiro aTabuleiro) {
		this.tabuleiro = aTabuleiro;
	}
	//função que fala se está certo ou errado o objetivo
	public boolean isObjetivo() {
		
		if(this.equals(EspacoDeEstados.OBJETIVO))
			return true;
		return false;
		
	}
	
	public void fazerAcao(Acao acao) {
		this.tabuleiro.fazerAcao(acao);
	}
	
	public Collection<Estado> geraFilhos(){
		
		Collection<Estado> filhos = new ArrayList<>();
		for(Acao acao: Acao.values()) {
			if(this.tabuleiro.isAcaoValida(acao)) {
				Estado filho = this.deepCopy();
				filho.fazerAcao(acao);
				filhos.add(filho);
			}
		}
		
		
		return filhos;
	}
	
	public Estado deepCopy() {
		return new Estado(this.tabuleiro.deepCopy());
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((tabuleiro == null) ? 0 : tabuleiro.hashCode());
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
		Estado other = (Estado) obj;
		if (tabuleiro == null) {
			if (other.tabuleiro != null)
				return false;
		} else if (!tabuleiro.equals(other.tabuleiro))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String res = "";
		res += this.tabuleiro + "\n";
		
		return res;
	}

}
