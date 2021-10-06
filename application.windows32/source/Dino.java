import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class Dino extends PApplet {

float dinoY,c,x,i;
float obs=500;
boolean jump=false;
boolean cocado=false;
boolean ia = false;
boolean jogo;int escolha =0;
int rand,pontos;boolean inicio = true,bot=false;
IA dino;
public void setup(){

dinoY=290;
pontos=0;
c=500;x=0;i=2;
rand = PApplet.parseInt(random(0,5));
jogo=false;
}
public void draw(){
background(0,178,255);
if(escolha==2){
dino = new IA();
dino.setup();
ia = dino.escolha(obs);
bot=true;
}
if(jogo==true){
if(inicio==true){
fill(255,255,255);
rect(290,180,140,60);
textSize(15);
fill(0,0,0);
text("Jogar contra a IA" ,295,210);
fill(255,255,255);
rect(90,180,140,60);
textSize(15);
fill(0,0,0);
text("Jogar normalmente" ,90,210);
}
fill(455,173,47);
stroke(20);
circle(c-=0.1f,10,200);
if(obs>0){
  obstaculos();
}else if(obs<0){
  pontos++;
  rand = PApplet.parseInt(random(x,150));
  if(rand>=100 && rand<=110){
  if(x<85){
  x+=2;
  }else{
  x=100;
  }
  obs=500;
  obstaculos();
  }
}
fill(100,100,45);
rect(0,350,500,10);
fill(133,87,35);
rect(0,350,500,300);

fill(255,255,255);
rect(c+50,105,90,35);
fill(255,255,255);
rect(c-55,135,90,35);
if(inicio==false){
fill(0,0,0);
textSize(25);
text("pontos "+pontos,30,450);
if(ia==true){
jump=true;
}
if(jump==true){
  fill(10,10,500);
  rect(30,dinoY-=10,40,60);
  if(dinoY==100){
    jump=false;
  }
  }else if(jump==false){
  if(dinoY==290){
    fill(10,10,500);
   rect(30,dinoY,40,60);
  }else if(dinoY<290){
    fill(10,10,500);
  rect(30,dinoY+=10,40,60);
  }
}

colisor();
}
  }else{
    
    background(0,178,255);
    textSize(25);
    fill(10,10,10);
    text("VOCÊ PERDEU \n SUA PONTUAÇÂO \n FOI DE "+pontos,160,200);
    fill(255,255,255);
    rect(170,290,90,40);
    textSize(15);
    fill(0,0,0);
    text("Continuar?" ,180,310);
    
    fill(455,173,47);
    stroke(20);
    circle(c,10,200);
    obstaculos();
    
    fill(100,100,45);
    rect(0,350,500,10);
    fill(133,87,35);
    rect(0,350,500,300);
    
    fill(255,255,255);
    rect(c+50,105,90,35);
    fill(255,255,255);
    rect(c-55,135,90,35);
    
    fill(10,10,500);
    rect(30,dinoY,40,60);
    reiniciar();
  }
}
public void reiniciar(){
  jogo=true;
  dinoY=290;
  c=500;x=0;i=2;
  obs=500;
  rand = PApplet.parseInt(random(0,5));
  jump=false;
  pontos=0;
  if(bot==true){
  dino.zerar();
  }
}
public void mouseClicked(){
  if(jogo==false){
    if(mouseX>=80 && mouseX<=250 && mouseY>=250 && mouseY<=330){
      reiniciar();
    }
  }else{
    if(mouseX>=150 && mouseX<=320 && mouseY>=80 && mouseY<=220){
      escolha = 2;
      inicio=false;
    }
    if(mouseX>=10 && mouseX<=200 && mouseY>=80 && mouseY<=220){
        escolha = 1;
       inicio=false;
    }
  }
}
public void colisor(){
  if(inicio==false){
  if(obs>=0 && obs<=70 && dinoY<=320 && dinoY>=230){
  System.out.println("Colidiu");
  jogo = false;
  }
  }
}
public void keyPressed(){
if(key=='w' && dinoY==290){
  jump=true;
}
if(key=='s'){
  jump=false;
  cocado=true;
}
}
public void obstaculos(){
  System.out.println(pontos);
  fill(10,500,50);
  if(jogo==true && inicio==false){
  i+=0.009f;
  }else{
  i=0;
  }
  if(i>12){
    i=12;
  }
  if(pontos>1000){
    i=18;
  }
  fill(0,255,0);
  rect(obs-=i,290,30,70);
}
class IA{
  float peso;
  int pesoa;
  int pesob;
  public void setup(){
   pesoa = PApplet.parseInt (random(145,190));
   pesob = PApplet.parseInt (random(125,180));
   peso = PApplet.parseInt (random(10,40));
   System.out.println("Peso a "+pesoa);
   System.out.println("Peso b "+pesob);
  }
  public float neuronio1(float enter){
    return enter+peso;
  }
  public float neuronio2(float enter){
    return enter-peso;
  }
  public boolean escolha(float x){
    float a1 = neuronio1(x);
    float a2 = neuronio2(x);
    if(a1+a2>pesob && a1+a2<pesoa){
      pesob+=5;
      pesoa+=5;
      return true;
    }else{
      return false;
    }
  }
  public void zerar(){
    pesoa = PApplet.parseInt (random(160,190));
    pesob = PApplet.parseInt (random(145,195));
    peso = PApplet.parseInt (random(10,40));
    System.out.println("Peso a"+pesoa);
    System.out.println("Peso b"+pesob);
  }
  
}
  public void settings() { 
size(500,500); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "Dino" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
