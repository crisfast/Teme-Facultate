#include <iostream>
#include <stdlib.h>

using namespace std;

typedef struct nod nod;

struct nod
      {int info;
       nod *adr;
       };

nod *prim, *ultim;
int n;
void creare_lista()
{nod *c;
 if(!prim)
    {prim=new nod;
    cout<<"Element: ";
     cin>>prim->info;
     ultim=prim;
    }
 else
     { c=new nod;
       cout<<"Element: ";
       cin>>c->info;
       ultim->adr=c;
       ultim=c;
       ultim->adr=0;
     }
}
void creaza_lista_circulara()
{ultim->adr=prim;
}

void afisare(nod *c)
{
    for(int i=1;i<=n;i++)
    {cout<<c->info<<" ";
     c=c->adr;}
}
void stergere(int poz)
{
    nod *sters, *c;
    c=prim;
    if(poz==1){
                      sters=prim;
                      prim=prim->adr;
                      creaza_lista_circulara();
                      delete (sters);
                      n--;
                     }
   if(poz==n){        for(int i=1;i<=n-2;i++)c=c->adr;
                      sters=ultim;
                      ultim=c;
                      creaza_lista_circulara();
                      delete (sters);
                      n--;
                     }
  for(int i=1;i<=n-2;i++)
   {
       if(i+1==poz){
                      sters=c->adr;
                      c->adr=c->adr->adr;
                      delete (sters);
                      n--;
                     }
       c=c->adr;
   }
}
void inserare(int poz,int nr)
{
    nod *nou,*c;
    c=prim;
    if(poz==1){nou=new nod;
               nou->info=nr;
               nou->adr=prim;
               prim=nou;
               n++;
               return;}
    if(poz==n+1){nou=new nod;
               nou->info=nr;
               ultim->adr=nou;
               ultim=nou;
               ultim->adr=prim;
               n++;
               return;
               }
    for(int i=2;i<=n;i++)
       {
       if(i==poz){
                      nou=new nod;
                      nou->info=nr;
                      nou->adr=c->adr;
                      c->adr=nou;
                      n++;
                     }
       c=c->adr;
       }
}

void stergereval(int val)
{
    nod *sters, *c;
    c=prim;
    if(prim->info==val){
                      cout<<"Element sters: "<<prim->info<<endl;
                      sters=prim;
                      prim=prim->adr;
                      creaza_lista_circulara();
                      delete (sters);
                      n--;
                      return;
                     }
   if(ultim->info==val){
                      for(int i=1;i<=n-2;i++) c=c->adr;
                      cout<<"Element sters: "<<ultim->info<<endl;
                      sters=ultim;
                      c->adr=prim;
                      ultim=c;
                      creaza_lista_circulara();
                      delete (sters);
                      n--;
                      return;
                     }
  for(int i=1;i<=n-2;i++)
   {
       if(c->adr->info==val){
                      cout<<"Element sters: "<<c->adr->info<<endl;
                      sters=c->adr;
                      c->adr=c->adr->adr;
                      delete (sters);
                      n--;
                      break;
                     }
       c=c->adr;
   }
}
int stergerek(nod *c,int k)
{int j=1;
nod *d;
    while(n!=1)
    {
      if(j%k==0)
      { d=c;
        c=c->adr;
        stergereval(d->info);
      }
         else c=c->adr;
         j++;
    }
}
int invers()
{
nod *aux1;;
nod *aux2=prim;
nod *p=prim;
nod *q,*r;
if(p!=NULL)
q=p->adr;
if(q!=NULL)
r=q->adr;

do{
    q->adr=p;

    p=q;
    q=r;
     r=r->adr;


    }while(p!=aux2);
    aux1=prim;
    prim=ultim;
    ultim=aux1;
}
int meniu()
{
    int optiune,ipoz,spoz,val,k;
    cout<<"Lista curenta: "; afisare(prim); cout<<endl;
    cout<<"Alege optiunea:"<<endl;
    cout<<"1.Inserare dupa pozitie."<<endl;
    cout<<"2.Stergere dupa pozitie."<<endl;
    cout<<"3.Inversare legaturi."<<endl;
    cout<<"4.Stergere din k in k elemente."<<endl;
    cout<<"5.Iesire."<<endl;

    cout<<"Optiune selectata: ";
    cin>>optiune;

    switch(optiune)
      {
      case 1:
          cout<<"Introduceti pozitia: ";
          cin>>ipoz;
          cout<<"Si acum valoarea dorita: ";
          cin>>val;
          inserare(ipoz,val);
          system("cls");
          break;
      case 2:
          cout<<"Ce pozitie doriti sa stergeti? ";
          cin>>spoz;
          stergere(spoz);
          system("cls");
          break;
      case 3:
          invers();
          system("cls");
          break;
      case 4:
           cout<<"Din cate in cate sa se stearga?(k): ";
           cin>>k;
           system("cls");
           afisare(prim);
           cout<<endl;
           stergerek(prim,k);
           cout<<endl;
           break;
      case 5:
          exit(0);
          break;
      default: cout<<"Optiunea nu exista!"<<endl;
      }
    meniu();
}

int main()
{
    cout<<"Numar elemente: ";
    cin>>n;
    for(int i=1;i<=n;i++)
      creare_lista();
    creaza_lista_circulara();
    afisare(prim);
    system("cls");
    meniu();
}
