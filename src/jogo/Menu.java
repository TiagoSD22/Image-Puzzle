package jogo;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

public class Menu {
	
	private JFrame frame;
			JButton atual,barra,limite,imagem, direita,esquerda,facil,normal,dificil,arquivo,jogar_personalizado;
			int opcao,dificuldade;
			JLabel titulo,check;
			String caminho;
			File diretorio,img_personalizada;
			Icon ft_personalizado;
			private static final int maximo = 11;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Menu window = new Menu(0,1,new File(System.getProperty("user.home")),null);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	public void DesenharMenu(){
		frame.requestFocus();
		if(opcao == 0){
			esquerda.setVisible(false);
			jogar_personalizado.setVisible(false);
		}
		if(opcao == maximo - 1){
			direita.setVisible(false);
			facil.setVisible(false);
			normal.setVisible(false);
			dificil.setVisible(false);
			check.setVisible(false);
			if(img_personalizada != null){
				jogar_personalizado.setVisible(true);
			}
			else{
				jogar_personalizado.setVisible(false);
			}
		}
		if(opcao > 0 && opcao < maximo - 1){
			direita.setVisible(true);
			esquerda.setVisible(true);
			facil.setVisible(true);
			normal.setVisible(true);
			dificil.setVisible(true);
			check.setVisible(true);
			jogar_personalizado.setVisible(false);
		}
		if(opcao < 10){
			imagem.setVisible(true);
			arquivo.setVisible(false);
			atual.setVisible(true);
			barra.setVisible(true);
			limite.setVisible(true);
			caminho = "/Imagens/" + String.valueOf(opcao + 1) + "/previa.jpg";
			imagem.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho)));
			caminho = "/Imagens/Numeros/" + String.valueOf(opcao + 1);
			if(opcao == 9){
				caminho += ".gif";
			}
			else{
				caminho += ".png";
			}
			atual.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource(caminho)));
		}
		else{
			imagem.setVisible(false);
			arquivo.setVisible(true);
			atual.setVisible(false);
			barra.setVisible(false);
			limite.setVisible(false);
		}
		if(dificuldade == 0){
			check.setBounds(facil.getX() - 40,283,40,40);
			facil.setBorderPainted(true);
			normal.setBorderPainted(false);
			dificil.setBorderPainted(false);
			facil.setBorder(BorderFactory.createLineBorder(new Color(153,76,0), 2));
		}
		else if(dificuldade == 1){
			check.setBounds(normal.getX() - 40,283,40,40);
			facil.setBorderPainted(false);
			normal.setBorderPainted(true);
			dificil.setBorderPainted(false);
			normal.setBorder(BorderFactory.createLineBorder(new Color(153,76,0), 2));
		}
		else{
			check.setBounds(dificil.getX() - 40,283,40,40);
			facil.setBorderPainted(false);
			normal.setBorderPainted(false);
			dificil.setBorderPainted(true);
			dificil.setBorder(BorderFactory.createLineBorder(new Color(153,76,0), 2));
		}
		if(img_personalizada == null){
			arquivo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/personalizado.png")));
		}
		else{
			arquivo.setIcon(ft_personalizado);
		}
	}
	
	public Menu(int opcao,int dificuldade,File diretorio, File img){
		this.diretorio = diretorio;
		this.opcao = opcao;
		this.dificuldade = dificuldade;
		img_personalizada = img;
		if(img_personalizada != null){
			Image fotop = new ImageIcon(img_personalizada.getPath()).getImage().getScaledInstance(188, 180, Image.SCALE_SMOOTH);
		    Icon ftp = new ImageIcon(fotop);
			ft_personalizado = ftp;
		}
		Iniciar();
		frame.setVisible(true);
	}
	
	public void Iniciar(){
		frame = new JFrame("MENU");
		frame.setBounds(100, 100, 500, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setContentPane(new JLabel(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/menu.jpg"))));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(Main.class.getResource("/Imagens/icon.png")));
		frame.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/cursor_padrao.png")).getImage(),new Point(0,0),"cursor"));
		frame.setFocusable(true);
		frame.setResizable(false);
		
		titulo = new JLabel("");
		titulo.setBackground(Color.lightGray);
		titulo.setOpaque(false);
		//titulo.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		titulo.setBounds(75, 25, 326, 40);
		titulo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/titulo.gif")));
		frame.getContentPane().add(titulo);
		
		imagem = new JButton();
		imagem.setBounds(145, (frame.getHeight() - 200)/2, 188, 180);
		imagem.setCursor(Toolkit.getDefaultToolkit().createCustomCursor(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/cursor_mao.png")).getImage(),new Point(0,0),"cursor"));
		imagem.setBorderPainted(true);
		imagem.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));
		imagem.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(SwingUtilities.isLeftMouseButton(e)){
					frame.dispose();
					new Main(opcao + 1,dificuldade,diretorio,false,img_personalizada);
				}
			}
		});
		frame.getContentPane().add(imagem);
		
		esquerda = new JButton();
		esquerda.setBounds(imagem.getX() - 75, imagem.getY() + imagem.getHeight()/2 - 35, 80, 80);
		esquerda.setCursor(imagem.getCursor());
		esquerda.setOpaque(false);
		esquerda.setBackground(Color.lightGray);
		esquerda.setBorderPainted(false);
		esquerda.setFocusPainted(false);
		esquerda.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/esquerda.png")));
		esquerda.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(SwingUtilities.isLeftMouseButton(e)){
					opcao--;
					DesenharMenu();
				}
			}
		});
		frame.add(esquerda);
		
		direita = new JButton();
		direita.setBounds(imagem.getX() + imagem.getWidth() - 5, imagem.getY() + imagem.getHeight()/2 - 35, 80, 80);
		direita.setCursor(imagem.getCursor());
		direita.setBackground(Color.lightGray);
		direita.setOpaque(false);
		direita.setBorderPainted(false);
		direita.setFocusPainted(false);
		direita.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/direita.png")));
		direita.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				if(SwingUtilities.isLeftMouseButton(e)){
					opcao++;
					DesenharMenu();
				}
			}
		});
		frame.add(direita);
		
		limite = new JButton("");
		limite.setBounds((frame.getWidth() - 21)/2, 263, 25, 25);
		limite.setOpaque(false);
		limite.setBorderPainted(false);
		limite.setBackground(Color.lightGray);
		limite.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/Numeros/10.gif")));
		frame.getContentPane().add(limite);
		
		barra = new JButton("");
		barra.setBounds((frame.getWidth() - 45)/2, 263, 25, 25);
		barra.setOpaque(false);
		barra.setBorderPainted(false);
		barra.setBackground(Color.lightGray);
		barra.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/Numeros/barra.png")));
		frame.getContentPane().add(barra);
		
		atual = new JButton("");
		atual.setBounds((frame.getWidth() - 75)/2, 263, 25, 25);
		atual.setBackground(Color.lightGray);
		atual.setOpaque(false);
		atual.setBorderPainted(false);
		frame.getContentPane().add(atual);
		
		facil = new JButton();
		facil.setBackground(Color.lightGray);
		facil.setOpaque(false);
		facil.setBorderPainted(false);
		facil.setBounds(87,295,60,20);
		facil.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/facil.jpg")));
		facil.setCursor(imagem.getCursor());
		facil.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				dificuldade = 0;
				DesenharMenu();
			}
		});
		frame.getContentPane().add(facil);
		
		normal = new JButton();
		normal.setBackground(Color.lightGray);
		normal.setOpaque(false);
		normal.setBorderPainted(true);
		//normal.setBorder(BorderFactory.createLineBorder(cor, 2));
		normal.setBounds(213,295,60,20);
		normal.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/normal.jpg")));
		normal.setCursor(imagem.getCursor());
		normal.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				dificuldade = 1;
				DesenharMenu();
			}
		});
		frame.getContentPane().add(normal);
		
		dificil = new JButton();
		dificil.setBackground(Color.lightGray);
		dificil.setOpaque(false);
		dificil.setBorderPainted(false);
		dificil.setBounds(336,295,60,20);
		dificil.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/dificil.jpg")));
		dificil.setCursor(imagem.getCursor());
		dificil.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				dificuldade = 2;
				DesenharMenu();
			}
		});
		frame.getContentPane().add(dificil);
		
		check = new JLabel("");
		check.setBackground(Color.lightGray);
		//check.setBorder(BorderFactory.createLineBorder(Color.gray, 2));
		check.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/point.png")));
		frame.getContentPane().add(check);
		
		frame.addKeyListener(new KeyListener(){

			@Override
			public void keyPressed(KeyEvent k) {
				// TODO Auto-generated method stub
				if(k.getKeyCode() == KeyEvent.VK_RIGHT){
					//direita.doClick();
					if(opcao < 10){
						opcao++;
						DesenharMenu();
					}
				}
				else if(k.getKeyCode() == KeyEvent.VK_LEFT){
					if(opcao > 0){
						opcao--;
						DesenharMenu();
					}
				}
				else if(k.getKeyCode() == KeyEvent.VK_UP){
					if(!arquivo.isVisible()){
						if(dificuldade < 2){
							dificuldade++;
							DesenharMenu();
						}
					}
				}
				else if(k.getKeyCode() == KeyEvent.VK_DOWN){
					if(!arquivo.isVisible()){
						if(dificuldade > 0){
							dificuldade--;
							DesenharMenu();
						}
					}
				}
				else if(k.getKeyCode() == KeyEvent.VK_SPACE){
					if(jogar_personalizado.isVisible()){
						frame.dispose();
						new Main(opcao + 1,dificuldade,diretorio,true,img_personalizada);
					}
				}
				else if(k.getKeyCode() == KeyEvent.VK_ENTER){
					if(!arquivo.isVisible()){
						frame.dispose();
						new Main(opcao + 1,dificuldade,diretorio,false,img_personalizada);
					}
					else{
						JFileChooser c = new JFileChooser();
						c.setCurrentDirectory(diretorio);
					    FileFilter filtro = new FileFilter() {
							@Override
							public boolean accept(File arg0) {
								// TODO Auto-generated method stub
								String name = arg0.getName();
								if(name.lastIndexOf('.')>0) {
					                 // get last index for '.' char
					                 int lastIndex = name.lastIndexOf('.');
					                 // get extension
					                 String str = name.substring(lastIndex);
					                 // match path name extension
					                 if(str.equals(".jpg") || str.equals(".jpeg") || str.equals(".png")
					                	|| str.equals(".gif") || str.equals(".TIFF") || str.equals(".BMP")) {
					                    return true;
					                 }
					            }
					            return false;
							}
			
							@Override
							public String getDescription() {
								// TODO Auto-generated method stub
								return "Imagens";
							}
				        };
				        c.setFileFilter(filtro);
				        c.addChoosableFileFilter(new FileNameExtensionFilter("JPG", "jpg","jpeg"));
				        c.addChoosableFileFilter(new FileNameExtensionFilter("PNG","png"));
				        c.addChoosableFileFilter(new FileNameExtensionFilter("GIF","gif"));
					    int opcao = c.showDialog(frame,"Escolha uma imagem");
					    diretorio = c.getCurrentDirectory();
					    if(opcao == JFileChooser.APPROVE_OPTION){
						    Image foto = new ImageIcon(c.getSelectedFile().getPath()).getImage().getScaledInstance(188, 180, Image.SCALE_SMOOTH);
						    Icon ft = new ImageIcon(foto);
						    ft_personalizado = ft;
					    	arquivo.setIcon(ft);
					    	img_personalizada = c.getSelectedFile();
					    	jogar_personalizado.setVisible(true);
					    }
					}
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
		
		jogar_personalizado = new JButton();
		jogar_personalizado.setBounds(imagem.getX() + (imagem.getWidth() - 100)/2, 262, 100, 25);
		jogar_personalizado.setCursor(imagem.getCursor());
		jogar_personalizado.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/jogar.png")));
		jogar_personalizado.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				arquivo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/load.gif")));
		    	Main m = new Main(11,1,diretorio,true,img_personalizada);
		    	if(!m.imagem_gerada){
		    		arquivo.setIcon(new ImageIcon(Toolkit.getDefaultToolkit().getClass().getResource("/Imagens/load.gif")));
		    	}
		    	if(m.imagem_gerada){
		    		frame.dispose();
		    	}
			}
		});
		frame.getContentPane().add(jogar_personalizado);
		
		arquivo = new JButton();
		arquivo.setBounds(imagem.getX(),imagem.getY(),188,180);
		arquivo.setBackground(Color.lightGray);
		arquivo.setOpaque(false);
		arquivo.setBorderPainted(true);
		arquivo.setBorder(BorderFactory.createLineBorder(Color.darkGray, 5));
		arquivo.setFocusable(false);
		arquivo.setCursor(direita.getCursor());
		arquivo.setVisible(false);
		arquivo.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent e){
				JFileChooser c = new JFileChooser();
				c.setCurrentDirectory(diretorio);
			    FileFilter filtro = new FileFilter() {
					@Override
					public boolean accept(File arg0) {
						// TODO Auto-generated method stub
						String name = arg0.getName();
						if(name.lastIndexOf('.')>0) {
			                 // get last index for '.' char
			                 int lastIndex = name.lastIndexOf('.');
			                 // get extension
			                 String str = name.substring(lastIndex);
			                 // match path name extension
			                 if(str.equals(".jpg") || str.equals(".jpeg") || str.equals(".png")
			                	|| str.equals(".gif") || str.equals(".TIFF") || str.equals(".BMP")) {
			                    return true;
			                 }
			            }
			            return false;
					}
	
					@Override
					public String getDescription() {
						// TODO Auto-generated method stub
						return "Imagens";
					}
		        };
		        c.setFileFilter(filtro);
		        c.addChoosableFileFilter(new FileNameExtensionFilter("JPG", "jpg","jpeg"));
		        c.addChoosableFileFilter(new FileNameExtensionFilter("PNG","png"));
		        c.addChoosableFileFilter(new FileNameExtensionFilter("GIF","gif"));
			    int opcao = c.showDialog(frame,"Escolha uma imagem");
			    diretorio = c.getCurrentDirectory();
			    if(opcao == JFileChooser.APPROVE_OPTION){
				    Image foto = new ImageIcon(c.getSelectedFile().getPath()).getImage().getScaledInstance(188, 180, Image.SCALE_SMOOTH);
				    Icon ft = new ImageIcon(foto);
				    ft_personalizado = ft;
			    	arquivo.setIcon(ft);
			    	img_personalizada = c.getSelectedFile();
			    	jogar_personalizado.setVisible(true);
			    }
			}
		});
		frame.getContentPane().add(arquivo);
		
		DesenharMenu();
	}
}
