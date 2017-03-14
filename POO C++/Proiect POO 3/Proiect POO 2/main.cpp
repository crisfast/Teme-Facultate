#include <iostream>
#include<fstream>
using namespace std;

    class lista;

    class nod
    {
          int info;
          nod *adr;

       public:
           nod():adr(NULL){}
           nod(int val):info(val),adr(NULL){}
           //nod(int val,nod* adr):info(val), adr(adr){}

           //int getinfo(){ return info; }
           //nod *getadr(){ return adr; }
           friend class lista;
           friend istream& operator>>(istream& , lista&);
           friend ostream& operator<<(ostream& , lista&);
           friend ifstream& operator>>(ifstream& , lista&);
           friend ofstream& operator<<(ofstream& , lista&);
           friend lista operator+(lista&,lista&);
           friend lista operator*(lista&,lista&);
           friend lista operator-(lista&,lista&);
           friend class perechi;
           friend class mperechi;
    };

     class lista
     {
        nod *prim;
        nod *ultim;
        int n,val;

        public:
            lista();
            lista(int val);
            void inserare(int val);
            void eliminare(int val);
            int cautare(int val);
            void multime();
            void stergere(int);
            friend istream& operator>>(istream& , lista&);
            friend ostream& operator<<(ostream& , lista&);
            friend ifstream& operator>>(ifstream& , lista&);
            friend ofstream& operator<<(ofstream& , lista&);
            friend lista operator+(lista&,lista&);
            friend lista operator*(lista&,lista&);
            friend lista operator-(lista&,lista&);
            friend class mperechi;
            friend class perechi;
     };
     lista::lista()
     {
         prim=ultim=NULL;
     }
     lista::lista(int val)
     {
         prim = new nod(val);
         ultim = prim;
     }

void lista::inserare(int val)
{
        {if(prim==NULL)
           {prim=new nod;
            prim->info=val;
            prim->adr=0;
            ultim=prim;                                                                                                                                                              //            si ultimul
       }
     else
       {nod *c;
        c=new nod;
      c->info=val;
      ultim->adr=c;
      ultim=c;
      ultim->adr=0;
      }
}
}
istream& operator>>(istream& in, lista& c)
{ int val;
  cout<<"numarul de elemente al listei: ";
  in>>c.n;
  for(int i=0;i<c.n;i++)
     {
      cout<<"Element: ";
      in>>val;
      c.inserare(val);
     }
 return in;
}
ifstream& operator>>(ifstream& in, lista& c)
{   int val;
    if(in>>c.n==NULL) cin>>c;
      else{
              for(int i=0;i<c.n;i++)
                {
                    in>>val;
                    c.inserare(val);
               }

          }
return in;
}
ostream& operator<<(ostream& out, lista& d)
{
        nod *c=d.prim;
        if (d.prim == NULL)
        cout << "Lista este goala" << endl;
         else{

        while (c != NULL) {
        out <<c->info<<" ";
        c = c->adr;}
        out << endl;}
 return out;
}
ofstream& operator<<(ofstream& out, lista& d)
{
        nod *c=d.prim;
        if (d.prim == NULL)
         out<< "Lista este goala" << endl;
         else{

        while (c != NULL) {
        out <<c->info<<" ";
        c = c->adr;}
        out << endl;}
 return out;
}
void lista::multime()
{
    nod *c=prim;
    while(c!=NULL)
    {
        while(cautare(c->info)>=2){stergere(c->info);}
        c=c->adr;
    }
}
void lista::stergere(int val)
{nod *c,*a;
 c=prim;
 if(prim->info==val)
   {a=prim;
    prim=prim->adr;
    delete a;}
 else
    {while(c->adr->info!=val &&c)
       c=c->adr;
     a=c->adr;
     c->adr=a->adr;
     if(a==ultim) ultim=c;
     delete a;}
}
int lista::cautare(int x)
{
    int gasite=0;
    nod *c=prim;
    while(c!=NULL)
    {
        if(c->info==x) gasite++;
        c=c->adr;
    }
    return gasite;
}
lista operator+(lista& a,lista& b)
{
    lista c;
    nod *d=a.prim;
    while(d!=NULL)
    {
       if(c.cautare(d->info)==0) c.inserare(d->info);
       d=d->adr;
    }
    nod *e=b.prim;
    while(e!=NULL)
    {
       if(c.cautare(e->info)==0) c.inserare(e->info);
       e=e->adr;
    }
    return c;
}
lista operator*(lista& a,lista& b)
{
    lista c;
    nod *d=a.prim;
    while(d!=NULL)
    {
       if(b.cautare(d->info)==1) c.inserare(d->info);
       d=d->adr;
    }
    return c;
}
lista operator-(lista& a,lista& b)
{
    lista c;
    nod *d=a.prim;
    while(d!=NULL)
    {
       if(b.cautare(d->info)==0) c.inserare(d->info);
       d=d->adr;
    }
    return c;
}
//Partea cu multimi
class mperechi;

    class pereche
    {
          int nr1,nr2;
          pereche *adr;

       public:
           pereche():adr(NULL){}
           pereche(int val1,int val2):nr1(val1),nr2(val2),adr(NULL){}
           //nod(int val,nod* adr):info(val), adr(adr){}

           //int getinfo(){ return info; }
           //nod *getadr(){ return adr; }
           friend class mperechi;
           friend ostream& operator<<(ostream& , mperechi&);
           friend ofstream& operator<<(ofstream&, mperechi&);
    };

     class mperechi
     {
        pereche *prim;
        pereche *ultim;
        int val1,val2;

        public:
            mperechi();
            mperechi(int val1,int val2);
            void inserare(int val1,int val2);
            friend ostream& operator<<(ostream&,mperechi&);
            mperechi cartezian(lista,lista);
            friend ofstream& operator<<(ofstream&, mperechi&);
     };
     mperechi::mperechi()
     {
         prim=ultim=NULL;
     }
     mperechi::mperechi(int val1,int val2)
     {
         prim = new pereche(val1,val2);
         ultim = prim;
     }
void mperechi::inserare(int val1,int val2)
{if(prim==NULL)
           {prim=new pereche;
            prim->nr1=val1;
            prim->nr2=val2;
            prim->adr=0;
            ultim=prim;                                                                                                                                                              //            si ultimul
           }
     else
            {pereche *c;
             c=new pereche;
             c->nr1=val1;
             c->nr2=val2;
             ultim->adr=c;
             ultim=c;
             ultim->adr=0;
            }
}
mperechi mperechi::cartezian(lista a,lista b)
{
   mperechi c;
   nod *d=a.prim;
   nod *e;
   while(d!=NULL)
   {
     e=b.prim;
     while(e!=NULL)
     {
      c.inserare(d->info,e->info);
      e=e->adr;
     }
     d=d->adr;
   }
return c;
}
ostream& operator<<(ostream& out, mperechi& d)
{
        pereche *c=d.prim;
        if (d.prim == NULL)
        cout << "Lista este goala" << endl;
         else{

        while (c != NULL) {
        out <<"("<<c->nr1<<","<<c->nr2<<") ";
        c=c->adr;}
        out << endl;}
 return out;
}
ofstream& operator<<(ofstream& out, mperechi& d)
{
        pereche *c=d.prim;
        if (d.prim == NULL)
        cout << "Lista este goala" << endl;
         else{

        while (c != NULL) {
        out <<"("<<c->nr1<<","<<c->nr2<<") ";
        c=c->adr;}
        out << endl;}
 return out;
}
int main()
{
    lista a,b,c,d,e,f;
    int metafis;
    mperechi carte1,carte2;
    ifstream g("multime1.txt");
    ifstream h("multime2.txt");
    ofstream j("rezultate.txt");
    cout<<"Metoda afisare (0 pentru fisier, 1 pentru consola): "; cin>>metafis; cout<<endl;
    if(metafis==1)  cout<<"Multimea 1"<<endl;
    g>>a;
    a.multime();
    if(metafis==0) j<<"Multimea 1: "<<a;
         else {cout<<"Multimea este: ";cout<<a<<endl;}
    if(metafis==1)cout<<"Multimea 2"<<endl;
    h>>b;
    b.multime();
    if(metafis==0) j<<"Multimea 2: "<<b;
         else {cout<<"Multimea este: ";cout<<b<<endl;}

    c=a+b;
    d=a*b;
    e=a-b;
    f=b-a;

    if(metafis==1){cout<<"Reuniunea elem din multimea a si elem din multimea b este: "<<c;
                   cout<<"Intersectia elem din multimea a si elem din multimea b este: "<<d;
                   cout<<"Diferenta intre multimea a si multimea b: "<<e;
                   cout<<"Diferenta intre multimea b si multimea a: "<<f<<endl;}
            else { j<<"Reuniunea elem din multimea a si elem din multimea b este: "<<c;
                   j<<"Intersectia elem din multimea a si elem din multimea b este: "<<d;
                   j<<"Diferenta intre multimea a si multimea b: "<<e;
                   j<<"Diferenta intre multimea b si multimea a: "<<f<<endl;}

    carte1=carte1.cartezian(a,b);
    if(metafis==1) cout<<"Produsul cartezian intre A si B: "<<carte1;
          else j<<"Produsul cartezian intre A si B: "<<carte1;
    carte2=carte2.cartezian(b,a);
    if(metafis==1) cout<<"Produsul cartezian intre B si A: "<<carte2;
          else j<<"Produsul cartezian intre B si A: "<<carte2;
    if(metafis==0) cout<<"Totul a mers bine! Verifica fisierul!";
    g.close();
    h.close();
    j.close();
    return 0;
}
