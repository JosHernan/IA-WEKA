package com.weka;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import javax.imageio.ImageIO;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;

public class WekaImage {
	static File open;
    static BufferedImage imagen;
    static Color colores;
    static int red[][], green[][], blue[][];//matriz de datos del rgb
    static int altura, altura1, anchura, anchura1, mientras, y,
            aux1, aux2, aux3, aux4;
    static double p1, p2, p3, p4, v1, v2, v3, v4;
    static PrintStream salida;
    static String archivo, nombre, titulo, clases, archivo2;
    public static void main(String[] args) throws Exception {
		//generadorarff();
    	identificacion();
    	
	}
   
    public  static void generadorarff() throws FileNotFoundException, IOException{
        System.out.println("Obtencion del promedio y desviacion estandar.arff");
        archivo = "paweka.arff";
        titulo = "paweka";
       // clases = "{Jaime,Jose,Lemuel, Ponce}";
         clases="{Galileo,Mark,Steven}";
        //clases="{Steven}";
        imprimirarff(archivo, titulo, clases);
      //  while(mientras < 3){
        /*    if(mientras< 1){
                nombre = "Galileo";
            } else if (mientras >= 2 ){
                nombre = "Mark";
            } else if (mientras >= 3){
                nombre = "Steven";
            } *//*else if(mientras>=15 && mientras<20)
            {
                nombre="Ponce";
            }*/
        while(mientras<3){
        	if(mientras<1){
        		nombre="Galileo";
        	}else if(mientras<=1){
        		nombre="Mark";
        	}else if(mientras<=2){
        		nombre="Steven";
        	}
            
            archivo2 = "C:\\Users\\jghernan\\workspace\\IA\\images\\" + nombre +".jpg";
           
            leo(archivo2);
           
            promedio(red);
            imprimo(p1, p2, p3, p4, salida);
            promedio(green);
            imprimo(p1, p2, p3, p4, salida);
            promedio(blue);
            imprimo(p1, p2, p3, p4, salida);
            varianza(red);
            imprimo(v1, v2, v3, v4, salida);
            varianza(green);
            imprimo(v1, v2, v3, v4, salida);
            varianza(blue);
            imprimo(v1, v2, v3, v4, salida);
            System.out.println(nombre);
            salida.println(nombre);
          mientras++;
            y++;
            if (y == 3) {
                y = 0;
            }
        }
        salida.close();   
    }
    
    public  static void leo(String archivo) throws IOException{
        int i=0,j=0;
        open = new File(archivo);
        imagen = ImageIO.read(open);
        altura = imagen.getHeight();
        altura1 = altura / 2;
        anchura = imagen.getWidth();
        anchura1 = anchura / 2;
        red = new int[altura][anchura];
        green = new int[altura][anchura];
        blue = new int[altura][anchura];
        for (i = 0; i < imagen.getHeight(); i++) {
            for (j = 0; j < imagen.getWidth(); j++) {
                colores = new Color(imagen.getRGB(j, i));
                red[i][j] = colores.getRed();
                green[i][j] = colores.getGreen();
                blue[i][j] = colores.getBlue();
            }
        }
    }
    
    public static void promedio(int x[][]){
        int i=0,j=0;
        p1 = 0;p2 = 0;p3 = 0;p4 = 0;
        aux1 = 0;aux2 = 0; aux3 = 0;aux4 = 0;
        for (i = 0; i < altura; i++) {
            for (j = 0; j < anchura; j++) {
                if (j < anchura1 && i < altura1){
                    p1 = p1 + x[i][j];
                    aux1++;
                } else if (j >= anchura1 && i < altura1){
                    p2 = p2 + x[i][j];
                    aux2++;
                    
                    
                } else if (j < anchura1 && i >= altura1){
                    p3 = p3 + x[i][j];
                    aux3++;
                } else if (j >= anchura1 && i >= altura1){
                    p4 = p4 + x[i][j];
                    aux4++;
                }
            }
        }
        p1 = p1/aux1;
        p2 = p2/aux2;
        p3 = p3/aux3;
        p4 = p4/aux4;
    }
    
    public static void varianza(int[][] x){
        int k = 0, l = 0, m = 0, n = 0,i=0,j=0;
        double sum1 = 0, sum2 = 0, sum3 = 0, sum4 = 0;
        int arr1[], arr2[], arr3[], arr4[];
        arr1 = new int[aux1];
        arr2 = new int[aux2];
        arr3 = new int[aux3];
        arr4 = new int[aux4];
        for (i = 0; i < altura; i++) {
            for (j = 0; j < anchura; j++) {
                if (j < anchura1 && i < altura1){
                    arr1[k] = (int) Math.pow((x[i][j]- p1),2);
                    k++;
                } else if (j >= anchura1 && i < altura1){
                    arr2[l] = (int) Math.pow((x[i][j]- p2),2);
                    l++;
                } else if (j < anchura1 && i >= altura1){
                    arr3[m] = (int) Math.pow((x[i][j]- p3),2);
                    m++;
                } else if (j >= anchura1 && i >= altura1){
                    arr4[n] = (int) Math.pow((x[i][j]- p4),2);
                    n++;
                }
            }
        }
        for (i = 0; i < k; i++) {
            sum1 = sum1 + arr1[i];
            sum2 = sum2 + arr2[i];
            sum3 = sum3 + arr3[i];
            sum4 = sum4 + arr4[i];
        }
        v1 = sum1 / k;
        v2 = sum2 / l;
        v3 = sum3 / m;
        v4 = sum4 / n;
    }
    
    public static void imprimo(double a, double b, double c, double d, PrintStream salida){
        System.out.print(a + " , " + b + " , " + c + " , " + d + " , ");
        salida.print(a + " , " + b + " , " + c + " , " + d + " , ");
    }
    
    public static void imprimirarff(String archivo, String titulo, String clases) throws FileNotFoundException{
        salida = new PrintStream(new File(archivo));
        salida.println("@relation " + titulo);
        salida.println("@attribute promedior1 numeric");
        salida.println("@attribute promedior2 numeric");
        salida.println("@attribute promedior3 numeric");
        salida.println("@attribute promedior4 numeric");
        salida.println("@attribute promediog1 numeric");
        salida.println("@attribute promediog2 numeric");
        salida.println("@attribute promediog3 numeric");
        salida.println("@attribute promediog4 numeric");
        salida.println("@attribute promediob1 numeric");
        salida.println("@attribute promediob2 numeric");
        salida.println("@attribute promediob3 numeric");
        salida.println("@attribute promediob4 numeric");
        salida.println("@attribute varianzar1 numeric");
        salida.println("@attribute varianzar2 numeric");
        salida.println("@attribute varianzar3 numeric");
        salida.println("@attribute varianzar4 numeric");
        salida.println("@attribute varianzag1 numeric");
        salida.println("@attribute varianzag2 numeric");
        salida.println("@attribute varianzag3 numeric");
        salida.println("@attribute varianzag4 numeric");
        salida.println("@attribute varianzab1 numeric");
        salida.println("@attribute varianzab2 numeric");
        salida.println("@attribute varianzab3 numeric");
        salida.println("@attribute varianzab4 numeric");
        salida.println("@attribute nombresC" + clases);
        salida.println();
        salida.println("@data");
    }
    
    public static void identificacion() throws IOException, Exception{
        archivo = "out.arff";
        titulo = "Identificador";
        clases="{Galileo,Mark,Steven}";
        imprimirarff(archivo, titulo, clases);
        leo("C:\\Users\\jghernan\\workspace\\IA\\images\\busca.jpg");
        promedio(red);
        imprimo(p1, p2, p3, p4, salida);
        promedio(green);
        imprimo(p1, p2, p3, p4, salida);
        promedio(blue);
        imprimo(p1, p2, p3, p4, salida);
        varianza(red);
        imprimo(v1, v2, v3, v4, salida);
        varianza(green);
        imprimo(v1, v2, v3, v4, salida);
        varianza(blue);
        imprimo(v1, v2, v3, v4, salida);
        System.out.println("?");
        salida.print("?");
        salida.close();
        DataSource source = new DataSource("paweka.arff");
        Instances data = source.getDataSet();
        if (data.classIndex() == -1) data.setClassIndex(data.numAttributes() - 1);
              DataSource source1 = new DataSource("out.arff");
        Instances data1 = source1.getDataSet();
        if (data1.classIndex() == -1) data1.setClassIndex(data1.numAttributes() - 1);
        evaluacion(data, data1);

    }
    
    public static void evaluacion(Instances data, Instances data1) throws Exception{
        String clase;
        NaiveBayes bayes = new NaiveBayes();
        
        Instance instancia = data1.firstInstance();
        bayes.buildClassifier(data);
       int insclasbayes = (int) bayes.classifyInstance(instancia);
        clase = data.classAttribute().value(insclasbayes);
        System.out.println(clase);
    }


}
