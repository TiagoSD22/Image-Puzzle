package jogo;
import java.util.Random;

public class jogo {
	public int matriz[][];
	public int n_linhas,n_colunas;
	
	jogo(int n_linhas, int n_colunas){
		this.n_linhas = n_linhas;
		this.n_colunas = n_colunas;
		this.matriz = new int[n_linhas][n_colunas];
		int i,j;
		for(i = 0; i < n_linhas; i++){
			for(j = 0; j < n_colunas; j++){
				matriz[i][j] = i * n_linhas + j;
			}
		}
		Embaralhar();
	}
	
	public void Embaralhar(){
		Random rand = new Random();
		int i,j,novo_i,novo_j,aux;
		for(i = 0; i < n_linhas; i++){
			for(j = 0; j < n_colunas; j++){
				novo_i = rand.nextInt(n_linhas - 1);
				novo_j = rand.nextInt(n_colunas - 1);
				aux = matriz[i][j];
				matriz[i][j] = matriz[novo_i][novo_j];
				matriz[novo_i][novo_j] = aux;
			}
		}
	}
	
	public void Mostrar_Matriz(){
		int i,j;
		for(i = 0; i < n_linhas; i++){
			for(j = 0; j < n_colunas; j++){
				System.out.print(matriz[i][j] + " ");
			}
			System.out.println();
		}
	}
	
	public boolean Pode_Mover_pBaixo(){
		int i,j;
		for(i = 0; i < n_linhas - 1; i++){
			for(j = 0; j < n_colunas; j++){
				if(matriz[i + 1][j] == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean Pode_Mover_pCima(){
		int i,j;
		for(i = 1; i < n_linhas; i++){
			for(j = 0; j < n_colunas; j++){
				if(matriz[i - 1][j] == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean Pode_Mover_pEsquerda(){
		int i,j;
		for(i = 0; i < n_linhas; i++){
			for(j = 1; j < n_colunas; j++){
				if(matriz[i][j - 1] == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean Pode_Mover_pDireita(){
		int i,j;
		for(i = 0; i < n_linhas; i++){
			for(j = 0; j < n_colunas - 1; j++){
				if(matriz[i][j + 1] == 0){
					return true;
				}
			}
		}
		return false;
	}
	
	public void Mover_pBaixo(){
		int i,j;
		for(i = 0; i < n_linhas - 1; i++){
			for(j =0 ; j < n_colunas; j++){
				if(matriz[i + 1][j] == 0){
					matriz[i + 1][j] = matriz[i][j];
					matriz[i][j] = 0;
					return;
				}
			}
		}
	}
	
	public void Mover_pCima(){
		int i,j;
		for(i = 1; i < n_linhas; i++){
			for(j =0 ; j < n_colunas; j++){
				if(matriz[i - 1][j] == 0){
					matriz[i - 1][j] = matriz[i][j];
					matriz[i][j] = 0;
					return;
				}
			}
		}
	}
	
	public void Mover_pEsquerda(){
		int i,j;
		for(i = 0; i < n_linhas; i++){
			for(j = 1 ; j < n_colunas; j++){
				if(matriz[i][j - 1] == 0){
					matriz[i][j - 1] = matriz[i][j];
					matriz[i][j] = 0;
					return;
				}
			}
		}
	}
	
	public void Mover_pDireita(){
		int i,j;
		for(i = 0; i < n_linhas; i++){
			for(j = 0 ; j < n_colunas - 1; j++){
				if(matriz[i][j + 1] == 0){
					matriz[i][j + 1] = matriz[i][j];
					matriz[i][j] = 0;
					return;
				}
			}
		}
	}
	
	public boolean Pode_Mover_Posicao(int i, int j){
		try{
			if(matriz[i + 1][j] == 0 || matriz[i + 2][j] == 0 || matriz[i + 3][j] == 0){
				return true;
			}
		}
		catch(Exception e){
		}
		try{
			if(matriz[i - 1][j] == 0 || matriz[i - 2][j] == 0 || matriz[i - 3][j] == 0){
				return true;
			}
		}
		catch(Exception e){
		}
		try{
			if(matriz[i][j + 1] == 0 || matriz[i][j + 2] == 0 || matriz[i][j + 3] == 0){
				return true;
			}
		}
		catch(Exception e){
		}
		try{
			if(matriz[i][j - 1] == 0 || matriz[i][j - 2] == 0 || matriz[i][j - 3] == 0){
				return true;
			}
		}
		catch(Exception e){
		}
		return false;
	}
	
	public void Mover_Posicao(int i, int j){
		try{
			if(matriz[i-1][j] == 0){
				Mover_pCima();
			}
			if(matriz[i-2][j] == 0){
				Mover_pCima();
				Mover_pCima();
			}
			if(matriz[i-3][j] == 0){
				Mover_pCima();
				Mover_pCima();
				Mover_pCima();
			}
		}
		catch(Exception e){
		}
		try{
			if(matriz[i+1][j] == 0){
				Mover_pBaixo();
			}
			if(matriz[i + 2][j] == 0){
				Mover_pBaixo();
				Mover_pBaixo();
			}
			if(matriz[i+3][j] == 0){
				Mover_pBaixo();
				Mover_pBaixo();
				Mover_pBaixo();
			}
		}
		catch(Exception e){
		}
		try{
			if(matriz[i][j + 1] == 0){
				Mover_pDireita();
			}
			if(matriz[i][j+2] == 0){
				Mover_pDireita();
				Mover_pDireita();
			}
			if(matriz[i][j+3] == 0){
				Mover_pDireita();
				Mover_pDireita();
				Mover_pDireita();
			}
		}
		catch(Exception e){
		}
		try{
			if(matriz[i][j-1] == 0){
				Mover_pEsquerda();
			}
			if(matriz[i][j - 2] == 0){
				Mover_pEsquerda();
				Mover_pEsquerda();
			}
			if(matriz[i][j-3] == 0){
				Mover_pEsquerda();
				Mover_pEsquerda();
				Mover_pEsquerda();
			}
		}
		catch (Exception e){	
		}
	}
	
	public boolean Venceu(){
		int i,j;
		for(i = 0; i < n_linhas; i++){
			for(j = 0; j < n_colunas; j++){
				if(matriz[i][j] != 0){
					if(matriz[i][j] != i * n_linhas + j + 1){
						return false;
					}
				}
			}
		}
		return true;
	}
}
