class IA{
  float peso;
  int pesoa;
  int pesob;
  void setup(){
   pesoa = int (random(145,190));
   pesob = int (random(125,180));
   peso = int (random(10,40));
   System.out.println("Peso a "+pesoa);
   System.out.println("Peso b "+pesob);
  }
  float neuronio1(float enter){
    return enter+peso;
  }
  float neuronio2(float enter){
    return enter-peso;
  }
  boolean escolha(float x){
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
  void zerar(){
    pesoa = int (random(160,190));
    pesob = int (random(145,195));
    peso = int (random(10,40));
    System.out.println("Peso a"+pesoa);
    System.out.println("Peso b"+pesob);
  }
  
}
