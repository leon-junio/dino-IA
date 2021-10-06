float dinoY,c,x,i;
float obs=500;
boolean jump=false;
boolean cocado=false;
boolean ia = false;
boolean jogo;int escolha =0;
int rand,pontos;boolean inicio = true,bot=false;
IA dino;
void setup(){
size(500,500);
dinoY=290;
pontos=0;
c=500;x=0;i=2;
rand = int(random(0,5));
jogo=false;
}
void draw(){
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
circle(c-=0.1,10,200);
if(obs>0){
  obstaculos();
}else if(obs<0){
  pontos++;
  rand = int(random(x,150));
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
void reiniciar(){
  jogo=true;
  dinoY=290;
  c=500;x=0;i=2;
  obs=500;
  rand = int(random(0,5));
  jump=false;
  pontos=0;
  if(bot==true){
  dino.zerar();
  }
}
void mouseClicked(){
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
void colisor(){
  if(inicio==false){
  if(obs>=0 && obs<=70 && dinoY<=320 && dinoY>=230){
  System.out.println("Colidiu");
  jogo = false;
  }
  }
}
void keyPressed(){
if(key=='w' && dinoY==290){
  jump=true;
}
if(key=='s'){
  jump=false;
  cocado=true;
}
}
void obstaculos(){
  System.out.println(pontos);
  fill(10,500,50);
  if(jogo==true && inicio==false){
  i+=0.009;
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
