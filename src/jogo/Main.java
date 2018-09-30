package jogo;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Image;
import java.awt.Point;

import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

public class Main {

	private JFrame frame;
			JFrame fim;
			JFrame visualizar;
			JLabel info;
			JButton sair;
			JLabel wood;
			JButton novojogo;
			JButton voltarmenu;
			JButton ver_imagem;
			boolean pode_mover;
			boolean destacar;
			JButton highlight;
			int dificuldade;
			File diretorio;
			Icon[][] icones;
			boolean personalizado;
			Icon previa;
	
	public jogo tab;
		   int n_movimentos = 0;
		   int imagem;
		   boolean imagem_gerada = false;
		   //Sons som;
		   
	private JButton Painel;
			JLabel fundo_tabuleiro;
	        JLabel milhar_jogada,centena_jogada,dezena_jogada,unidade_jogada;
	        JLabel Jogadas;
	        JButton novo;
	        JButton botoes[][]; 
		   
	public void Recomecar(){
		fim.setVisible(false);
		pode_mover = true;
		destacar = false;
		//Sons.init();
		//Painel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Fontes/alive.png")));
		n_movimentos = 0;
		Jogadas.setText("Jogadas: 0");
		tab = new jogo(4,4);
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				botoes[i][j].setBorderPainted(false);
			}
		}
		Desenhar_Tabuleiro();
	}
		   
	/*public void Iniciar_Tabuleiro(){
    	String arquivo;
    	if(dificuldade == 1){
    		highlight.setEnabled(false);
    	}
    	for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				botoes[i][j].setBorderPainted(false);
				if(tab.matriz[i][j] != 0){
					arquivo = "/Imagens/" + String.valueOf(imagem)  + "/" + String.valueOf(tab.matriz[i][j]) + ".jpg";
					botoes[i][j].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(arquivo)));		
				}
				else{
					botoes[i][j].setIcon(null);	
				}
			}
		}
	}*/
	
	public void Desenhar_Numero_Jogadas(){
		int milhar = 0,centena = 0,dezena = 0,unidade = 0;
		int n = n_movimentos;
		if(dificuldade == 2){
			n = 500 - n_movimentos;
		}
		if(n < 10){
			milhar = 0;
			centena = 0;
			dezena = 0;
			unidade = n % 10;
		}
		else if(n < 100){
			milhar = 0;
			centena = 0;
			dezena = n / 10;
			unidade = n % 10;
		}
		else if(n < 1000){
			milhar = 0;
			centena = n / 100;
			dezena = (n / 10) % 10;
			unidade = n % 10;
		}
		else{
			milhar = n / 1000;
			centena = (n / 100) % 10;
			dezena = (n / 10) % 100;
			unidade = n % 10;
		}
		milhar_jogada.setText(String.valueOf(milhar));
		centena_jogada.setText(String.valueOf(centena));
		dezena_jogada.setText(String.valueOf(dezena));
		unidade_jogada.setText(String.valueOf(unidade));
	}
	
    public void Desenhar_Tabuleiro(){
    	frame.requestFocus();
    	//Jogadas.setText("Jogadas: " + String.valueOf(n_movimentos));
    	String arquivo;
    	if(dificuldade >= 1){
    		highlight.setVisible(false);
    	}
    	if(dificuldade == 2){
    		ver_imagem.setVisible(false);
    	}
    	if(!destacar){
    		highlight.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/marcar_on.png")));
    	}
    	else{
    		highlight.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/marcar_off.png")));
    	}
    	if(!personalizado){
	    	if(dificuldade == 0){
		    	for(int i = 0; i < 4; i++){
					for(int j = 0; j < 4; j++){
						if(tab.matriz[i][j] != 0){
							if(tab.matriz[i][j] == i * 4 + j + 1){
								arquivo = "/Imagens/" + String.valueOf(imagem) + "/Brilhosa/" + String.valueOf(tab.matriz[i][j]) + ".jpg";
								botoes[i][j].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(arquivo)));
								if(destacar){
									botoes[i][j].setBorderPainted(true);
									botoes[i][j].setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));
									
								}
								else{
									botoes[i][j].setBorderPainted(false);
								}
							}
							else{
								arquivo = "/Imagens/" + String.valueOf(imagem) + "/" + String.valueOf(tab.matriz[i][j]) + ".jpg";
								botoes[i][j].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(arquivo)));
							}
						}
						else{
							botoes[i][j].setBorderPainted(false);
							botoes[i][j].setIcon(null);
						}
					}
		    	}
	    	}
		    else if(dificuldade >= 1){
		    	for(int i = 0; i < 4; i++){
		    		for(int j = 0; j < 4; j++){
		    			if(tab.matriz[i][j] != 0){
		    				arquivo = "/Imagens/" + String.valueOf(imagem) + "/Brilhosa/" + String.valueOf(tab.matriz[i][j]) + ".jpg";
							botoes[i][j].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(arquivo)));
		    			}
		    			else{
		    				botoes[i][j].setIcon(null);
		    			}
		    		}
		    	}
		    }
    	}
    	else{
    		int valor,linha,coluna;
    		for(int i = 0; i < 4; i++){
	    		for(int j = 0; j < 4; j++){
	    			if(tab.matriz[i][j] != 0){
	    				valor = tab.matriz[i][j] - 1;
	    				linha = valor / 4;
	    				coluna = valor % 4;
						botoes[i][j].setIcon(icones[linha][coluna]);
	    			}
	    			else{
	    				botoes[i][j].setIcon(null);
	    			}
	    		}
	    	}
    	}
    	Desenhar_Numero_Jogadas();
    }
    
    public void Fim_de_Jogo(){
    	if(dificuldade == 2){
    		if(n_movimentos == 300){
    			pode_mover = false;
        		fim.setVisible(true);
        		fim.setTitle("PERDEU");
    		}
    	}
    	if(tab.Venceu()){
    		if(!personalizado){
    			String caminho = "/Imagens/" + String.valueOf(imagem) + "/Brilhosa/16.jpg";
    			botoes[3][3].setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho)));
    		}
    		else{
    			botoes[3][3].setIcon(icones[3][3]);
    		}
    		if(destacar){
    			botoes[3][3].setBorderPainted(true);
    			botoes[3][3].setBorder(BorderFactory.createLineBorder(Color.CYAN, 1));
    		}
    		pode_mover = false;
    		fim.setVisible(true);
    		fim.setTitle("Você venceu!");
    	}
    }  
    
    public void CriarImagemJogavel(File imagem){
    	icones = new Icon[4][4];
    	Image img = null;
    	try {
			img = ImageIO.read(imagem).getScaledInstance(188, 180, Image.SCALE_SMOOTH);
		} catch (IOException e) {e.printStackTrace();}
    	for(int i = 0; i < 4; i++){
    		for(int j = 0; j < 4; j++){
    			icones[i][j] = new ImageIcon(Toolkit.getDefaultToolkit().createImage(new FilteredImageSource(img.getSource(), new CropImageFilter(47 * j, 45 * i, 47, 45))));
    		}
    	}
    	imagem_gerada = true;
    }
    
	/**
	 * Create the application.
	 */
	public Main(int imagem, int dificuldade, File diretorio,boolean personalizado, File imagem_personalizado) {
		if(personalizado){
			try {
				previa = new ImageIcon(ImageIO.read(imagem_personalizado).getScaledInstance(188, 180, Image.SCALE_SMOOTH));
			} catch (IOException e1) {e1.printStackTrace();}
			CriarImagemJogavel(imagem_personalizado);
		}
		this.personalizado = personalizado;
		this.diretorio = diretorio;
		this.dificuldade = dificuldade;
		this.imagem = imagem;
		pode_mover = true;
		frame = new JFrame();
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Imagens/icon.png")));
		frame.setContentPane(new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/bg.jpg"))));
		frame.setBackground(Color.LIGHT_GRAY);
		frame.setBounds(10, 10,328,420);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setVisible(true);
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/cursor_padrao.png")).getImage(),new Point(0,0),"cursor"));
		
		tab = new jogo(4,4);
		frame.setTitle("Picture Puzzle");
		frame.getContentPane().setLayout(null);
		frame.setResizable(true);
		botoes = new JButton[4][4];
		//int largura_bt = 50;
		int e = 0;
		//int x0 = (frame.getWidth() - ((4 * largura_bt) + (3 * e)))/2;
		
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				botoes[i][j] = new JButton("");
				botoes[i][j].setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/cursor_mao.png")).getImage(),new Point(0,0),"cursor"));
				botoes[i][j].setBackground(Color.LIGHT_GRAY);
				botoes[i][j].setOpaque(false);
				botoes[i][j].setBorderPainted(false);
				botoes[i][j].setFocusPainted(false);
				botoes[i][j].setBounds(60 + j*(47 + e), 120 + i*(45 + e),47,45);
				frame.getContentPane().add(botoes[i][j]);
			}
		}
		
		Color Cor_dentro = new Color(83,83,83);
		Color Cor_fora = new Color(83,83,83);
		
		fundo_tabuleiro = new JLabel();
		//fundo_tabuleiro.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/tab.jpg")));
		fundo_tabuleiro.setBounds(55, 116,199,189);
		fundo_tabuleiro.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED,Cor_dentro,Cor_fora), BorderFactory.createBevelBorder(BevelBorder.LOWERED, Cor_dentro, Cor_fora)));
		frame.getContentPane().add(fundo_tabuleiro);
		frame.setFocusable(true);
		frame.addKeyListener(new KeyListener() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				// TODO Auto-generated method stub
				if(visualizar.isVisible()){
					visualizar.dispose();
				}
				if(arg0.getKeyCode() == KeyEvent.VK_UP){
					if(pode_mover){
						if(tab.Pode_Mover_pCima()){
							tab.Mover_pCima();
							n_movimentos++;
							Desenhar_Tabuleiro();
							Fim_de_Jogo();
						}
					}
					else{
						Fim_de_Jogo();
					}
				}
				if(arg0.getKeyCode() == KeyEvent.VK_DOWN){
					if(pode_mover){
						if(tab.Pode_Mover_pBaixo()){
							tab.Mover_pBaixo();
							n_movimentos++;
							Desenhar_Tabuleiro();
							Fim_de_Jogo();
						}
					}
					else{
						Fim_de_Jogo();
					}
				}
				if(arg0.getKeyCode() == KeyEvent.VK_LEFT){
					if(pode_mover){
						if(tab.Pode_Mover_pEsquerda()){
							tab.Mover_pEsquerda();
							n_movimentos++;
							Desenhar_Tabuleiro();
							Fim_de_Jogo();
						}
					}
					else{
						Fim_de_Jogo();
					}
				}
				if(arg0.getKeyCode() == KeyEvent.VK_RIGHT){
					if(pode_mover){
						if(tab.Pode_Mover_pDireita()){
							tab.Mover_pDireita();
							n_movimentos++;
							Desenhar_Tabuleiro();
							Fim_de_Jogo();
						}
					}
					else{
						Fim_de_Jogo();
					}
				}
				if(arg0.getKeyCode() == KeyEvent.VK_BACK_SPACE){
					frame.dispose();
					new Menu(imagem-1,dificuldade,diretorio,imagem_personalizado);
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub
			}
		});
		
		//som = new Sons();
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				botoes[i][j].addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent e) {
						int x = 0,y = 0;
						while(e.getSource() != botoes[x][y]){
							y++;
							if(y == 4){
								y = 0;
								x++;
							}
						}
						if(SwingUtilities.isLeftMouseButton(e)){
							if(pode_mover){
								if(tab.Pode_Mover_Posicao(x, y)){
									tab.Mover_Posicao(x, y);
									if(visualizar.isVisible()){
										visualizar.setVisible(false);
									}
									n_movimentos++;
									Desenhar_Tabuleiro();
									Fim_de_Jogo();
								}
							}
							else{
								Fim_de_Jogo();
							}
						}
					}
				});
			}
		}
		
		Painel = new JButton("");
		Painel.setCursor(botoes[0][0].getCursor());
		Painel.setBackground(Color.lightGray);
		Painel.setOpaque(false);
		Painel.setBorderPainted(false);
		Painel.setFocusPainted(false);
		Painel.setToolTipText("CLIQUE AQUI PARA VOLTAR AO MENU");
		Painel.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/casa.png")));
		Painel.setBounds(frame.getWidth()/2 - 26,330, 40, 40);
		Painel.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frame.dispose();
				if(fim.isVisible()){
					fim.dispose();
				}
				if(visualizar.isVisible()){
					visualizar.dispose();
				}
				new Menu(imagem-1,dificuldade,diretorio,imagem_personalizado);
			}
		});
		frame.getContentPane().add(Painel);
		
		Jogadas = new JLabel("");
		Jogadas.setBounds(frame.getWidth() - 145, 19, 80, 15);
		//Jogadas.setFont(new Font("Arial",Font.ITALIC,12));
		//Jogadas.setForeground(Color.YELLOW);
		Jogadas.setHorizontalAlignment(SwingConstants.CENTER);
		Jogadas.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/jogadas.png")));
		frame.getContentPane().add(Jogadas);
		
		Color C = new Color(255,240,103);
		
		milhar_jogada = new JLabel("0");
		milhar_jogada.setForeground(C);
		milhar_jogada.setBounds(Jogadas.getX() + Jogadas.getWidth() + 5,Jogadas.getY() + 2, 10, 10);
		frame.getContentPane().add(milhar_jogada);
		
		centena_jogada= new JLabel("0");
		centena_jogada.setForeground(C);
		centena_jogada.setBounds(Jogadas.getX() + Jogadas.getWidth() + 15,Jogadas.getY() + 2, 10, 10);
		frame.getContentPane().add(centena_jogada);
		
		dezena_jogada = new JLabel("0");
		dezena_jogada.setForeground(C);
		dezena_jogada.setBounds(Jogadas.getX() + Jogadas.getWidth() + 25,Jogadas.getY() + 2, 10, 10);
		frame.getContentPane().add(dezena_jogada);
		
		unidade_jogada = new JLabel("0");
		unidade_jogada.setForeground(C);
		unidade_jogada.setBounds(Jogadas.getX() + Jogadas.getWidth() + 35,Jogadas.getY() + 2, 10, 10);
		frame.getContentPane().add(unidade_jogada);
		
		wood = new JLabel();
		wood.setBounds(1,1,frame.getWidth(),50);
		wood.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/ret.jpg")));
		frame.getContentPane().add(wood);
		
		novo = new JButton("");
		novo.setBackground(Color.gray);
		novo.setOpaque(false);
		novo.setBorderPainted(false);
		novo.setFocusPainted(false);
		novo.setToolTipText("RECOMEÇAR");
		novo.setCursor(botoes[0][0].getCursor());
		novo.setBounds(frame.getWidth()/2 - 30, 55, 40, 40);
		novo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/replay.png")));
		frame.getContentPane().add(novo);
		
		novo.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Recomecar();
			}
		});	
		
		highlight = new JButton();
		highlight.setBackground(Color.lightGray);
		highlight.setOpaque(false);
		highlight.setBorderPainted(false);
		highlight.setFocusPainted(false);
		highlight.setToolTipText("CLIQUE PARA DESTACAR AS PEÇAS CORRETAS");
		highlight.setCursor(botoes[0][0].getCursor());
		highlight.setBounds(260,110,40,40);
		highlight.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/marcar_on.png")));
		highlight.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(SwingUtilities.isLeftMouseButton(e)){
					destacar = !destacar;
					Desenhar_Tabuleiro();
					Fim_de_Jogo();
				}
			}
		});
		frame.getContentPane().add(highlight);
		
		fim = new JFrame();
		fim.setBounds(frame.getWidth()/2,frame.getHeight()/2,255,260);
		fim.getContentPane().setLayout(null);
		fim.setContentPane(new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/bg_final.jpg"))));
		fim.setCursor(frame.getCursor());
		fim.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		fim.setVisible(false);
		info = new JLabel();
		info.setBounds(((fim.getWidth() - 1)/2) - 165, 10, 330, 20);
		info.setHorizontalAlignment(SwingConstants.CENTER);
		fim.getContentPane().add(info);
		novo = new JButton();
		novo.setCursor(botoes[0][0].getCursor());
		novo.setBounds(((fim.getWidth() - 1)/2) - 93, 90, 160, 40);
		novo.setBackground(Color.LIGHT_GRAY);
		novo.setOpaque(false);
		novo.setBorderPainted(false);
		novo.setFocusable(false);
		novo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/novojogoseta.png")));
		novo.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				Recomecar();
			}
		});
		fim.getContentPane().add(novo);
		sair = new JButton();
		sair.setCursor(novo.getCursor());
		sair.setBounds(((fim.getWidth() - 1)/2) - 80, 170, 160, 40);
		sair.setBorderPainted(false);
		sair.setOpaque(false);
		sair.setBackground(Color.LIGHT_GRAY);
		sair.setFocusable(false);
		sair.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/sair.png")));
		sair.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frame.dispose();
				fim.dispose();
				System.exit(0);
			}
		});
		fim.getContentPane().add(sair);
		voltarmenu = new JButton();
		voltarmenu.setCursor(botoes[0][0].getCursor());
		voltarmenu.setBounds(((fim.getWidth() - 1)/2) - 85, 10, 170,60);
		voltarmenu.setBackground(Color.LIGHT_GRAY);
		voltarmenu.setFocusable(false);
		voltarmenu.setOpaque(false);
		voltarmenu.setBorderPainted(false);
		voltarmenu.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/menuseta.png")));
		voltarmenu.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				frame.dispose();
				fim.dispose();
				new Menu(imagem-1,dificuldade,diretorio,imagem_personalizado);
			}
		});
		fim.getContentPane().add(voltarmenu);
		
		visualizar = new JFrame();
		visualizar.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Imagens/view.png")));
		if(!personalizado){
			String caminho = "/Imagens/" + String.valueOf(imagem) + "/previa.jpg";
			visualizar.setContentPane(new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho))));
		}
		else{
			visualizar.setContentPane(new JLabel(previa));
		}
		visualizar.setBackground(Color.LIGHT_GRAY);
		visualizar.setBounds(frame.getX() + frame.getWidth() + 10, 170,205,220);
		visualizar.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		visualizar.setVisible(false);
		
		ver_imagem = new JButton("");
		ver_imagem.setBackground(Color.gray);
		ver_imagem.setOpaque(false);
		ver_imagem.setBorderPainted(false);
		ver_imagem.setFocusPainted(false);
		ver_imagem.setToolTipText("VER IMAGEM");
		ver_imagem.setCursor(botoes[0][0].getCursor());
		ver_imagem.setBounds(263,275, 40, 40);
		ver_imagem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/olho.png")));
		frame.getContentPane().add(ver_imagem);
		
		ver_imagem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(!visualizar.isVisible()){
					visualizar.setVisible(true);
					visualizar.setFocusable(false);
					frame.requestFocus();
				}
			}
		});	
		
		Desenhar_Tabuleiro();
	}
}