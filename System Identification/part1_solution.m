clear
close all
clc
load('proj_fit_11.mat');
x=id.X;
x1=id.X{1,1};
x2=id.X{2,1};
y=id.Y;
surf(x1,x2,y); title('Datele initiale')

xflat=[];
x1flat=[];
for i=1:length(x1)
    x1flat=[x1flat,x1];
end
x2flat=[];
for i=1:length(x2)
    aux=[];
    for j=1:length(x2)
        aux=[aux,x2(i)];
    end
    x2flat=[x2flat,aux];
end
xflat=[x1flat;x2flat];
yflat=reshape(y,1,[]);

m=21;   % cel mai bun grad gasit

%---------Identificarea--------
phi=[];
for i=1:length(xflat)
x=1;
  for j=1:m
      x=[x,xflat(1,i)^j,xflat(2,i)^j];
  end
  for j=1:m
        for k=1:m
          if j+k<=m
             x=[x,xflat(1,i)^j*xflat(2,i)^k];
          end
        end
  end
ph=x;
phi=[phi;ph];
end

A=phi\yflat';
y_est= phi*A;
e=yflat'-y_est;
figure;
y_est_=reshape(y_est,length(x1),length(x2));
surf(x1,x2,y_est_);
MSE=1/length(e)*sum(e.^2);
title(['MSE Identificare=',num2str(MSE)]);

%------------Validarea-----------
xval=val.X;
x1val=val.X{1,1};
x2val=val.X{2,1};
yval=val.Y;

xflatval=[];
x1flatval=[];
for i=1:length(x1val)
    x1flatval=[x1flatval,x1val];
end
x2flatval=[];
for i=1:length(x2val)
    aux=[];
    for j=1:length(x2val)
        aux=[aux,x2val(i)];
    end
    x2flatval=[x2flatval,aux];
end
xflatval=[x1flatval;x2flatval];
yflatval=reshape(yval,1,[]);

phival=[];
for i=1:length(xflatval)
x=1;
  for j=1:m
          x=[x,xflatval(1,i)^j,xflatval(2,i)^j];
  end
  for j=1:m
        for k=1:m
          if j+k<=m
              x=[x,xflatval(1,i)^j*xflatval(2,i)^k];
          end
        end
  end
phval=x;
phival=[phival;phval];
end

y_estval= phival*A;
eval=yflatval'-y_estval;
figure;
y_estval_=reshape(y_estval,length(x1val),length(x2val));
surf(x1val,x2val,y_estval_);
MSEval=1/length(eval)*sum(eval.^2);
title(['MSE Validare=',num2str(MSEval)]);