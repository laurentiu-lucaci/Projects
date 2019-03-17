clc
clear all
close all
load('iddata-03');
uid=id.u;
yid=id.y;
yval=val.y;
uval=val.u;

% plot([uid,yid]);
% title('Datele initiale');
% xlabel('x');
% ylabel('y');

na=2;
nb=2;
m=3;
yid1=yid;
yid=[zeros(1,na+nb),yid'];
uid=[zeros(1,na+nb),uid'];

psi=[];
L=[];
Matr_y=[];
Matr_u=[];
linie=[];
matrice_grad=[];
    for i=1+na+nb:length(yid)
        linie=[];
        Matr_y=[];
        Matr_u=[];
        for j=1:na
            Matr_y=[Matr_y yid(i-j)];
        end
        for j=1:nb
            Matr_u=[Matr_u uid(i-j)];
        end 
        linie=[Matr_y Matr_u];
        matrice_grad=[matrice_grad;linie];
    end
    aux=matrice_grad;
    if m>1 
        for i=2:m
            z=aux.^i;
            matrice_grad=[matrice_grad z];
        end
    end
    
aux_final=matrice_grad;
matrice_combinari=[];
GR=ones((na+nb)*m,1);
for i=2:m
    GR((na+nb)*(i-1)+1:(na+nb)*i)=i;
end

MATR_comb=[];
for i=1:length(yid1)
        x=aux_final(i,1:length(aux_final(1,:))-length(linie));
        linie_combinari=[];
        for var=2:m
            COMB_gri=combntns(1:length(x),var);
            clc
            for k=1:length(COMB_gri)
                produs=1;
                grad=0;
                for j=1:var
                    produs=produs*x(COMB_gri(k,j));
                    grad=grad+GR(COMB_gri(k,j));
                end
                if grad<=m
                    linie_combinari=[linie_combinari, produs];
                end
            end
        end
        MATR_comb=[MATR_comb;linie_combinari];
end

matr_grad=[];
for i=1:length(matrice_grad)
    matr_grad = [matr_grad; matrice_grad(i,1:(na+nb)*(m-1))];
end

matrice_finala=[matr_grad MATR_comb];
q=ones(length(yid1),1);
matrice_finala=[q matrice_finala]; 

%-------------------identificarea-------------------

psi=matrice_finala;
teta=psi\yid1;
yc=psi*teta;
eid=yid1-yc;
MSEid=1/length(eid)*sum(eid).^2;
plot([yid1,yc]);
legend('yid1','yc')
title({['m= ',num2str(m),'  na= ',num2str(na),'  nb= ',num2str(nb)];['MSEid= ',num2str(MSEid)]});
xlabel('x');
ylabel('y');
figure;

%---------------------validarea--------------------

yval1=yval;
yval=[zeros(1,na+nb),yval'];
uval=[zeros(1,na+nb),uval'];

psi_val=[];
L_val=[];
Matr_y_val=[];
Matr_u_val=[];
linie_val=[];
matrice_grad_val=[];
    for i=1+na+nb:length(yval)
        linie_val=[];
        Matr_y_val=[];
        Matr_u_val=[];
        for j=1:na
            Matr_y_val=[Matr_y_val yval(i-j)];
        end
        for j=1:nb
            Matr_u_val=[Matr_u_val uval(i-j)];
        end 
        linie_val=[Matr_y_val Matr_u_val];
        matrice_grad_val=[matrice_grad_val;linie_val];
    end
    aux_val=matrice_grad_val;
    if m>1 
        for i=2:m
            z=aux_val.^i;
            matrice_grad_val=[matrice_grad_val z];
        end
    end
    
aux_final_val=matrice_grad_val;
matrice_combinari_val=[];
GR_val=ones((na+nb)*m,1);
for i=2:m
    GR_val((na+nb)*(i-1)+1:(na+nb)*i)=i;
end

MATR_comb_val=[];
for i=1:length(yval1)
        x=aux_final_val(i,1:length(aux_final_val(1,:))-length(linie_val));
        linie_combinari_val=[];
        for var=2:m
            COMB_gri_val=combntns(1:length(x),var);
            clc;
            for k=1:length(COMB_gri_val)
                produs=1;
                grad=0;
                for j=1:var
                    produs=produs*x(COMB_gri_val(k,j));
                    grad=grad+GR_val(COMB_gri_val(k,j));
                end
                if grad<=m
                    linie_combinari_val=[linie_combinari_val, produs];
                end
            end
        end
        MATR_comb_val=[MATR_comb_val;linie_combinari_val];
end

matr_grad_val=[];
for i=1:length(matrice_grad_val)
    matr_grad_val = [matr_grad_val; matrice_grad_val(i,1:(na+nb)*(m-1))];
end

matrice_finala_val=[matr_grad_val MATR_comb_val];
q=ones(length(yval1),1);
matrice_finala_val=[q matrice_finala_val]; 

psi_val=matrice_finala_val;
yc_val=psi_val*teta;
eval=yval1-yc_val;
MSEval=1/length(eval)*sum(eval).^2;
plot([yval1,yc_val]);
legend('yval1','yc_val')
title({['m= ',num2str(m),'  na= ',num2str(na),'  nb= ',num2str(nb)];['MSEval= ',num2str(MSEval)]});
xlabel('x');
ylabel('y');
