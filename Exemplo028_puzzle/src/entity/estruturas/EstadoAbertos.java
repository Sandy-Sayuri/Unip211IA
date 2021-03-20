package entity.estruturas;

import entity.Estado;
//atualiza a matriz
public interface EstadoAbertos {
	
	public void push(Estado estado);
	public Estado pop();
	public int size();

}
