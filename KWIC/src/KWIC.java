import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class KWIC {

    public static class titulo
    {
        String titulos;
        Object Palabras;
        Object iteraciones;
        titulo sgte;
    }

    public static void main(String[] args) {
        String str="titulos.txt";
        titulo cab=null;
        cab=enviar(str,cab);
        listado(cab);
        buscar(cab,"twain huckLebeRrY AveNturas");
        System.out.println(cantidad(str));
    }

    public static void listado(titulo c) {
        String pal[];
        while(c!=null) {
            System.out.print("Titulo : "+c.titulos);
            System.out.print("\nPalabras clave : ");
            for(int i=0;i< ((String[])c.Palabras).length ;i++){
                System.out.print("/"+((String[])c.Palabras)[i]);
            }
            System.out.print("\n");
            for(int i=0;i< ((String[])c.iteraciones).length ;i++){
                System.out.println("-->"+((String[])c.iteraciones)[i]);
            }
            System.out.print("\n");
            c=c.sgte;
        }

    }

    public static String buscar(titulo c,String bus){
        String titulo="";
        String busq[]=bus.split(" ");
        Boolean centi=false;
        int cc=0;
        while(c!=null) {
           // System.out.print("Titulo : "+c.titulos);
            for(int i=0;i< ((String[])c.iteraciones).length ;i++){
                String pal[]=((String[])c.iteraciones)[i].split(" ");
                for(int k=0;k<busq.length;k++){
                    //System.out.println(" palabra busqueda : " + busq[k]);
                    for(int u=0;u<pal.length;u++){

                       // System.out.println(" palabras titulo : " + pal[u]);
                        if(centi){

                            if(busq[k].equalsIgnoreCase(pal[u])){
                                u--;
                                cc++;
                                break;
                            }
                        }else{
                            if(busq[k].equalsIgnoreCase(pal[0])){

                                u--;
                                cc++;
                                centi=true;
                                break;
                            }
                        }

                    }
                    if(k+1==busq.length){
                        if(cc==busq.length){
                            System.out.println("Titulo : "+c.titulos);
                            System.out.println("ENCONTRADO : =>"+((String[])c.iteraciones)[i]);

                        }
                        centi=false;
                        cc=0;
                    }
                }
            }


            c=c.sgte;
        }
        return titulo;
    }

    public static titulo adicionar(titulo a,String pal,String[] palabras,String[] cir) {
        titulo c=null;
        titulo b=new titulo();
        b.titulos=pal;
        b.Palabras=palabras;
        b.iteraciones=cir;
        b.sgte=null;
        if(a==null) {
            a=b;
        }else {
            c=a;
            while(c.sgte!=null) {
                c=c.sgte;
            }
            c.sgte=b;
        }
        return a;
    }

    public static titulo enviar(String str, titulo cab){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File ("C:\\Users\\PORCEL\\KWIC\\src\\"+str);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);
            String linea;
            while((linea=br.readLine())!=null) {

                cab=adicionar(cab,linea,palabras_clave(linea),Circular(linea,palabras_clave(linea)));
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return cab;
    }

    public static String[] Circular(String str,String[] palabra){
        String pal[] = str.split(" ");
        String an="",des="",sum="";
        String cir[] = new String[palabra.length];
        Boolean x=false;
        for(int i=0;i<palabra.length;i++){
            sum="";
            an="";
            des="";
            x=false;
            for(int j=0;j<pal.length;j++){
                if(palabra[i].equalsIgnoreCase(pal[j])){
                    x=true;
                }
                if(x){
                    an=an+pal[j]+" ";
                }else{
                    des=des+pal[j]+" ";
                }
            }
            sum=an+des;
            cir[i]=sum;
        }
        return cir;
    }

    public static String[] palabras_clave(String str){
        String v[]={"2ª","las","los","con","como","que","no","lo","en","de","la","y","al","el","o","a","por","tu","si","-","1","2","3","4","5","6","7","8","9","0","del","Los","El","La","Una","Un","Uso","En","Las","Los","los","Del"};
        String pal[] = str.split(" ");
        String cont="";
        Boolean centi=true,centi2=true;
        for(int i=0;i<pal.length;i++){
            for(int j=0;j<v.length;j++){
                if(pal[i].equalsIgnoreCase(v[j])){
                    centi=true;

                    break;
                }else{
                    centi=false;
                }
            }
            if(centi!=true){
                if(i!=v.length-1){

                    cont=cont+pal[i]+" ";
                }else{
                    cont=cont+pal[i];
                }

            }

        }
        String palabras[] = cont.split(" ");


        return palabras;
    }

    public static int cantidad(String str){
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;
        int con=0;
        try {
            archivo = new File ("C:\\Users\\PORCEL\\KWIC\\src\\"+str);
            fr = new FileReader (archivo);
            br = new BufferedReader(fr);

            // Lectura del fichero

            while((br.readLine())!=null) {
                con++;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if( null != fr ){
                    fr.close();
                }
            }catch (Exception e2){
                e2.printStackTrace();
            }
        }
        return con;
    }

}
