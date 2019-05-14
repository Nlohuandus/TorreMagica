package juego;

public class Fisica {
	public static boolean colision(Mago mago, Viga[] vigas) {
		for(int i=0;i<vigas.length;i++) {
			if(mago.getPosY()+mago.getAlto()==vigas[i].posy) {	
				if(mago.getPosX()>vigas[i].bordeIz && mago.getPosX()<vigas[i].bordeDer) {
					return true;
				}
			}
		}

		return false;
	}
	
	public static void margen(int margen, Mago mago) {
		if(mago.getPosY()<-5) {
			mago.inicio(margen);
		}
		if(mago.getPosY()>margen && mago.muerte==false) {
			mago.inicio(0);
		}
		
	}
	
	
	public static boolean congelar(Mago enemigo, Mago mago) {
			//if(personajes[i].getPosY()<=getPosY() && personajes[i].getPosY()>=getPosY()-ancho)
		if(mago.lDisparo.size()>0) {
			int i=mago.lDisparo.size()-1;
			//System.out.println("posy disparo "+mago.lDisparo.get(i).getY());
			//System.out.println("posy enemigo: "+enemigo.getPosY());
			if(enemigo.getPosY()<=mago.lDisparo.get(i).getY() && enemigo.getPosY()>=mago.lDisparo.get(i).getY()-50 ) {
				if(enemigo.getPosX()<=mago.lDisparo.get(i).getX() && enemigo.getPosX()>=mago.lDisparo.get(i).getX()-50 ) {	
					return true;
				}else if(enemigo.getPosX()<=mago.lDisparo.get(i).getX() && enemigo.getPosX()>=mago.lDisparo.get(i).getX()+50 ) {	
					return true;
				}
			}
		
		}
		
		return false;
	}
	
	public static void colision(Mago[]personajes,Mago muerto, int i) {

				for(int j=i+1;j<personajes.length;j++) {
					if(personajes[j].isEstado()) {
						if(personajes[j].getPosY()<=muerto.getPosY() && personajes[j].getPosY()>=muerto.getPosY()-muerto.ancho) {
							if(personajes[j].getPosX()<=muerto.getPosX() && personajes[j].getPosX()>=muerto.getPosX()-muerto.ancho) {
								personajes[j].setEstado(false);
								personajes[j].muerte=true;
								personajes[j].contacto=true;
							}else if(personajes[j].getPosX()>=muerto.getPosX() && personajes[j].getPosX()<=muerto.getPosX()+muerto.ancho) {
								personajes[j].setEstado(false);
								personajes[j].muerte=true;
								personajes[j].contacto=true;
							}
						}
					
					}	
				}

			}


}

