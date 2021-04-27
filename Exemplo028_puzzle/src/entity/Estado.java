package entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Estado {
	
	Tabuleiro tabuleiro;
	List<Acao> acoes;
	

	public Estado(Tabuleiro aTabuleiro) {
		this.tabuleiro = aTabuleiro;
		this.acoes = new LinkedList<>();
	}
	
	public Estado(Tabuleiro aTabuleiro, List<Acao> aAcoes) {
		this(aTabuleiro);
		this.acoes = aAcoes;
		
	}
	//função que fala se está certo ou errado o objetivo
	public boolean isObjetivo() {
		
		if(this.equals(EspacoDeEstados.OBJETIVO))
			return true;
		return false;
		
	}
	
	public void fazerAcao(Acao acao) {
		this.acoes.add(acao);
		this.tabuleiro.fazerAcao(acao);
	}
	
	public Collection<Estado> geraFilhos(){
		
		Collection<Estado> filhos = new ArrayList<>();
		for(Acao acao: Acao.values()) {
//			System.out.println("testado a acao " + acao);
//			System.out.println("resultando em " + this.tabuleiro.isAcaoValida(acao));
			if(this.tabuleiro.isAcaoValida(acao)) {
				Estado filho = this.deepCopy();
				filho.fazerAcao(acao);
				filhos.add(filho);
			}
		}
		
		
		return filhos;
	}
	
	public Estado deepCopy() {
		List<Acao> novasAcoes = new LinkedList<>(this.acoes);
		return new Estado(this.tabuleiro.deepCopy(), novasAcoes);
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
		String res = "\n";
		res += "nivel: " + this.acoes.size() + "\n";
		res += this.tabuleiro + "\n";
		for(Acao acao: this.acoes) {
			res += acao + ", ";
		}
		res += "\n";
		
		return res;
	}

}
