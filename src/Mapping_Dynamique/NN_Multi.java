package Mapping_Dynamique;

import Application.Tache;
import Architecture.Create_NOC;
import Simulation.Simulator;
import Simulation.StaticParametre;

public class NN_Multi {
	
	private boolean plac� ;
	private int temps_recherche;
	private int x;
	private int y;
	public int Energie;
	
	
	public NN_Multi(){
		
		temps_recherche=0;
		
		Energie=0;
	}

/////////////////////////////////////////////////// NN avec la strategie de packing G,B,H,D	//////////////////////////////
	
public   void start_GBHD(Tache tachePlacer,int x,int y) throws InterruptedException 

{
	this.x=x;
	this.y=y;

	
while(plac�==false)	
{
	//recherche sur le meme proc 
		recherche_meme_proc(tachePlacer,this.x,this.y);
	if(plac�==false)
	// recherche a  gauche
	recherche_gauche(tachePlacer,this.x,this.y);
	//recherche en bas
	if (plac�==false)
		recherche_bas(tachePlacer,this.x,this.y);
	//recherche en haut
	if (plac�==false)
		recherche_haut(tachePlacer,this.x,this.y);
	
	// recherche a droite
	if (plac�==false)
		recherche_droite(tachePlacer,this.x,this.y);	
	

	
	
	
// verifier si la t�che a �t� plac� ou non 

  if(plac�==false)
  {
	  int i=0;
	while(plac�==false && i<=StaticParametre.Limit_NOC_x-1)
	{
	 // temps_recherche=temps_recherche+100;
	  
	   i++; 
	  refaire(tachePlacer,getX(),getY()-i); // refaire la recherche � partir du proc gauche
	  

	 
	  if(plac�==false)		  
			  
      refaire(tachePlacer,getX()+i,getY()); //refaire la recherche � partir du proc bas
	  
	  
	  
	  
	  if(plac�==false)
	      refaire(tachePlacer,getX()-i,getY()); //refaire la recherche � partir du proc haut
	  
	  
	if (plac�==false)
		  refaire(tachePlacer,getX(),getY()+i); //refaire la recherche � partir du proc droit
	
	if(plac�==false && i==StaticParametre.Limit_NOC_x-1) {
								int c=0;
								plac�=true;
								if(StaticParametre.not_mapped.isEmpty())
								StaticParametre.not_mapped.add(tachePlacer);
								else
									{
										for(int k=0;k<StaticParametre.not_mapped.size();k++)
										{
											if(StaticParametre.not_mapped.get(k).equals(tachePlacer))
												{c=1;break;}
										}
									if(c!=1)	StaticParametre.not_mapped.add(tachePlacer);
										
									}
							}
	  }
   }
  
  
 }



}

//////////////////////////////////////////
public  void refaire(Tache tachePlacer,int x,int y) throws InterruptedException
{
	// recherche a gauche
	
	recherche_gauche(tachePlacer,x,y);
		//recherche en bas
	
	if (plac�==false)
			recherche_bas(tachePlacer,x,y);
		
		//recherche en haut
		if (plac�==false)
			recherche_haut(tachePlacer,x,y);
		
		// recher a droite
		if (plac�==false)
			recherche_droite(tachePlacer,x,y);	
		
	
	
	
}





///////////////////////////////////////


public  void lancer(Tache t,int x,int y) throws InterruptedException
{


t.x=x;
t.y=y;

t.mapped=true;

t.debut_execution=Simulator.Tnow+this.temps_recherche;
t.debut_routage=Simulator.Tnow+this.temps_recherche;




Create_NOC.getNOC()[x][y].setMem(t.getTailleTache(Create_NOC.getNOC()[x][y].getType()),1);


Create_NOC.getNOC()[x][y].File.add(t);
Simulator.set_temps_recherche(temps_recherche);
Simulator.setEnergy(Energie);



}








////////////////////////////////


//////////////////////////////////////////////////////Spiral ////////////////////////////////////////////

public   void start_Spiral(Tache tachePlacer,int x,int y) throws InterruptedException 
{
this.x=x;
this.y=y;

plac�=false;																																																																			//if (StaticParametre.f.equals("scenario1.xml")) { this.=this.+3500;this.Energie=this.Energie+380;}	

while(plac�==false)	
{
//recherche sur le meme proc 
recherche_meme_proc(tachePlacer,this.x,this.y);

	if(plac�==false)	
	//recherche gauche 
	recherche_gauche(tachePlacer,this.x,this.y);

if(plac�==false)
	recherche_bas(tachePlacer,this.x,this.y);
	
if(plac�==false)
	recherche_haut(tachePlacer,this.x,this.y);

if(plac�==false)
	recherche_droite(tachePlacer,this.x,this.y);


	if(plac�==false)
		recherche_haut_gauche(tachePlacer,this.x,this.y);

	

				if(plac�==false)
					recherche_haut_droite(tachePlacer,this.x,this.y);


				if(plac�==false)
					recherche_bas_gauche(tachePlacer,this.x,this.y);	
				
						if(plac�==false)
							recherche_bas_droite(tachePlacer,this.x,this.y);

							

																																												

//////////////// verifier si la tache a �t� plac� lors de la premiere ex�cution du Spiral 
								
								
		if(plac�==false)
		{
			int i=0;
			this.temps_recherche+=10;
			
			while(plac�==false && i<=StaticParametre.Limit_NOC_x-1)
			{
				i++;
			refaire_Spiral(tachePlacer,this.x,this.y-i);//reprendre la recherche � partir du processeur gauche
			
			if(plac�==false)
					refaire_Spiral(tachePlacer,this.x+i,this.y);//reprencdre la recherche � partir du processeur bas
				
			
			 if(plac�==false) 
                	 refaire_Spiral(tachePlacer,this.x-i,this.y);//reprendre la recherche � partir du processeur haut
             
			
			 
			 if(plac�==false)
  				refaire_Spiral(tachePlacer,this.x,this.y+i);//reprencdre la recherche � partir du processeur droit
			
			 
			if(plac�==false)
				refaire_Spiral(tachePlacer,this.x-i,this.y-i);//reprendre la recherche � partir du processeur haut gauche
				
			
                
 			
                 		if(plac�==false)
                      			refaire_Spiral(tachePlacer,this.x-i,this.y+i);//reprencdre la recherche � partir du processeur haut droit
                 		  
                 			
                 		if(plac�==false)
								refaire_Spiral(tachePlacer,this.x+i,this.y-i);//reprencdre la recherche � partir du processeur bas gauche
		
                 		
                 					if(plac�==false)
                      						refaire_Spiral(tachePlacer,this.x+i,this.y+i);//reprencdre la recherche � partir du processeur bas droit
                 					
                 							
                 								
         								if(plac�==false && i==StaticParametre.Limit_NOC_x-1) {
         													int c=0;
                 											plac�=true;
                 											if(StaticParametre.not_mapped.isEmpty())
   															StaticParametre.not_mapped.add(tachePlacer);
                															else
                 																{
                 																	for(int k=0;k<StaticParametre.not_mapped.size();k++)
                 																	{
                 																		if(StaticParametre.not_mapped.get(k).equals(tachePlacer))
                 																			{c=1;break;}
                 																	}
                 																if(c!=1)	StaticParametre.not_mapped.add(tachePlacer);
                 																	
                 																}
                 														}
			
			
			}                								
		}
	}
		
		



}





/////////////////////////// recherche mm proc
public void recherche_meme_proc(Tache tachePlacer,int x,int y) throws InterruptedException
{
temps_recherche+=10;
	


for(int i=0;i<tachePlacer.getType().size();i++)
{
	

if(Create_NOC.getNOC()[x][y].getType() == tachePlacer.getType().get(i))
{
	
if (Create_NOC.getNOC()[x][y].getMem()>=tachePlacer.getTailleTache(tachePlacer.getType().get(i)))
{
	
	lancer(tachePlacer,x,y);
	plac�=true;

	}
	}
	}
}



/////////////////////// 
 


public  void recherche_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	if(y-1>=0 && y<=StaticParametre.Limit_NOC_y-1 && x>=0 && x<=StaticParametre.Limit_NOC_x-1)
	{
	temps_recherche=temps_recherche+100;
	
	this.Energie+=1;
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
	if(Create_NOC.getNOC()[x][y-1].getType() == tachePlacer.getType().get(i))
	{
		if (Create_NOC.getNOC()[x][y-1].getMem()>=tachePlacer.getTailleTache(tachePlacer.getType().get(i)))
			
		{
			
			lancer(tachePlacer,x,y-1);
			plac�=true;
			
		}
	}
	}
	
	}
}


/////////////////////////////////////	
	
public  void recherche_bas(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if(x+1<=StaticParametre.Limit_NOC_x-1 && x>=0 && y>=0 && y<=StaticParametre.Limit_NOC_y-1)
	  {temps_recherche=temps_recherche+100;
	   this.Energie+=1; 
	  for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	  {
	  if(Create_NOC.getNOC()[x+1][y].getType() == tachePlacer.getType().get(i))
				{
		  				
						if (Create_NOC.getNOC()[x+1][y].getMem()>=tachePlacer.getTailleTache(tachePlacer.getType().get(i)))
						{
		
									plac�=true;
		lancer(tachePlacer,x+1,y);
		
		
		
						}
						
	
				}
	  }
     }
	
																			}

//////////////////////////////////////////



public  void recherche_haut(Tache tachePlacer,int x,int y) throws InterruptedException
{
	
	
	
	if(x-1>=0 && x<=StaticParametre.Limit_NOC_x-1 && y>=0 && y<=StaticParametre.Limit_NOC_y-1)
	{temps_recherche=temps_recherche+100;
	this.Energie+=1; 
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
	if(Create_NOC.getNOC()[x-1][y].getType() == tachePlacer.getType().get(i)) 
	  {
		  
		 
		if (Create_NOC.getNOC()[x-1][y].getMem()>=tachePlacer.getTailleTache(tachePlacer.getType().get(i)))
		{
			
			lancer(tachePlacer,x-1,y);
			plac�=true;
			
		}
	  }
		
		
	   }
	}	

	
	

}


//////////////////////////////////


public  void recherche_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if (y+1<=StaticParametre.Limit_NOC_y-1 && y>=0 && x>=0 && x<=StaticParametre.Limit_NOC_x-1)
	{temps_recherche=temps_recherche+100;	
	this.Energie+=1;

	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x][y+1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x][y+1].getMem()>=tachePlacer.getTailleTache(tachePlacer.getType().get(i)))
		{
			
			lancer(tachePlacer,x,y+1);
			plac�=true;
			}
			    }	
	}
	}
}


////////////////////////// verifier le proceseur haut_gauche
public  void recherche_haut_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	if (y-1>=0 && x-1>=0 && y<=StaticParametre.Limit_NOC_y-1 && x<=StaticParametre.Limit_NOC_x-1)
	{temps_recherche=temps_recherche+100;
	this.Energie+=1;
	
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x-1][y-1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x-1][y-1].getMem()>=tachePlacer.getTailleTache(tachePlacer.getType().get(i)))
		{
			
					
			lancer(tachePlacer,x-1,y-1);
			plac�=true;
			}
			    }	
	}
	}
}

///////////////////////////// rechercher le processeur haut_droite 


public  void recherche_haut_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if (x-1>=0 && y+1<=StaticParametre.Limit_NOC_y-1 && x<=StaticParametre.Limit_NOC_x-1 && y>=0)
	{temps_recherche=temps_recherche+100;
	this.Energie+=1;

	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x-1][y+1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x-1][y+1].getMem()>=tachePlacer.getTailleTache(tachePlacer.getType().get(i)))
		{
			
					
			lancer(tachePlacer,x-1,y+1);
			plac�=true;
			}
			    }	
	}
	}
}


////////////////////////////// rechercher le processeur bas_gauche 

public  void recherche_bas_gauche(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	if (x+1<=StaticParametre.Limit_NOC_x-1 && y-1>=0 && x>=0 && y<=StaticParametre.Limit_NOC_y-1)
	{temps_recherche=temps_recherche+100;
	this.Energie+=1;

	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x+1][y-1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x+1][y-1].getMem()>=tachePlacer.getTailleTache(tachePlacer.getType().get(i)))
		{
			
					
			lancer(tachePlacer,x+1,y-1);
			plac�=true;
			}
			    }	
	}
	}
}




//////////////////////////////////// recherche processeur bas_droite 

public  void recherche_bas_droite(Tache tachePlacer,int x,int y) throws InterruptedException{
	
	
	
	
	if (x+1<=StaticParametre.Limit_NOC_x-1 && y+1<=StaticParametre.Limit_NOC_y-1 && x>=0 && y>=0)
	{temps_recherche=temps_recherche+100;
	this.Energie+=1;
	for(int i=0;i<tachePlacer.getType().size();i++) //parcourir la liste des types de la tache
	{
		if (Create_NOC.getNOC()[x+1][y+1].getType() == tachePlacer.getType().get(i))
	    {
		if (Create_NOC.getNOC()[x+1][y+1].getMem()>=tachePlacer.getTailleTache(tachePlacer.getType().get(i)))
		{
			
					
			lancer(tachePlacer,x+1,y+1);
			plac�=true;
			}
			    }	
		}
	}
}


///////////////////////////////////// 



public  void refaire_Spiral(Tache tachePlacer,int x,int y) throws InterruptedException
{
	
	recherche_gauche(tachePlacer,x,y);
	if(plac�==false)
		recherche_haut_gauche(tachePlacer,x,y);
			if(plac�==false)
				recherche_haut(tachePlacer,x,y);

				if(plac�==false)
					recherche_haut_droite(tachePlacer,x,y);

					if(plac�==false)
						recherche_droite(tachePlacer,x,y);

						if(plac�==false)
							recherche_bas_droite(tachePlacer,x,y);

							if(plac�==false)
								recherche_bas(tachePlacer,x,y);

								if(plac�==false)
									recherche_bas_gauche(tachePlacer,x,y);		
	
	
}







public  int getY(){
	return this.y;
}


public  int getX(){
	return this.x;
}



public  int Time(){
	return (this.temps_recherche);
	
      }

public  int Energie()
	{
	return(this.Energie);
	}

}
