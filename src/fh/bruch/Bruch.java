package fh.bruch;

public class Bruch
{
	/** 
	 * vorzeichen == true  -> Bruch hat positives Vorzeichen (+)
	 * vorzeichen == false -> Bruch hat negatives Vorzeichen (-)
	 */
	private boolean vorzeichen;
	private int zaehler, nenner;
	
	public Bruch (boolean vz, int z, int n)
	{
		this.vorzeichen = vz;
		
		this.zaehler = z;
		this.nenner = n;
		this.kuerzen();
	}
	
	private void kuerzen()
	{
		int ggt = this.ggt(this.zaehler, this.nenner);
		
		this.zaehler /= ggt;
		this.nenner  /= ggt;
	}
	
	private int ggt(int a, int b)
	{
		if (a == 0) 
			return b;
		
		int k = 0;
		
		while (((a % 2) == 0) && ((b % 2) == 0))
		{
			a = a/2;
			b = b/2;
			k++;			
		}
				
		int t;
		if (a % 2 == 0)
			t = a;
		else
			t = -b;
		
		while (t != 0)
		{	
			while ((t % 2) == 0)
			{
				t = t / 2;
			}

			if (t > 0) 
				a = t;
			else
				b = -t;	
			t = a - b;
			
		}
		
		return a * (1 << k);		
	}
	
	
	public boolean getVorzeichen() {
		return vorzeichen;
	}
	public int getZaehler() {
		return zaehler;
	}
	public int getNenner() {
		return nenner;
	}

	public boolean istGleich(Bruch p) {
		return this.vorzeichen == p.vorzeichen &&
			   this.zaehler == p.zaehler &&
			   this.nenner == p.nenner;
	}

	public int vergleiche(Bruch p) {
		if (this.vorzeichen && !p.vorzeichen) //vorzeichen sind nicht gleich
			return 1;
		else if (!this.vorzeichen && p.vorzeichen)
			return -1;
		else if (this.istGleich(p))
			return 0;		
		
		int kgv = (this.nenner * p.nenner) / this.ggt(this.nenner, p.nenner);
		
		int tz = this.zaehler * kgv / this.nenner;
		int pz = p.zaehler * kgv / p.nenner;
		
		if (this.vorzeichen && p.vorzeichen) // beide Br�che positiv
			return (tz > pz ? 1 : -1);
		else // beide negativ
			return (tz > pz ? -1 : 1);
	}
	
	public void multipliziere(Bruch b)
	{
		this.vorzeichen = this.vorzeichen == b.vorzeichen;
		this.zaehler *= b.zaehler;
		this.nenner *= b.nenner;
		
		this.kuerzen();
	}

	public void addiere(Bruch p) {
		int kgv = (this.nenner * p.nenner) / this.ggt(this.nenner, p.nenner);
		int tz = this.zaehler * kgv / this.nenner;
		int pz = p.zaehler * kgv / p.nenner;
		
		if (this.vorzeichen == p.vorzeichen)
		{
			this.zaehler = tz + pz;
			this.nenner = kgv;
			
			this.kuerzen();
		}
		else
		{
			int neuerZaehler = tz * (this.vorzeichen ? 1 : -1) + pz * (p.vorzeichen ? 1 : -1);
			this.vorzeichen = neuerZaehler >= 0;
			this.zaehler = neuerZaehler >= 0 ? neuerZaehler : -neuerZaehler;
			this.nenner = kgv;
			this.kuerzen();
		}
	}

	public void subtrahiere(Bruch p) {
		Bruch p2 = new Bruch(!p.vorzeichen, p.zaehler, p.nenner);
		this.addiere(p2);	
	}
	
	public String toString()
	{
		return ((!this.vorzeichen) ? "-" : "") + this.zaehler + "/" + this.nenner;
	}
}
