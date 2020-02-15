create or replace function callf (rollno integer) returns void as $$
declare
d date;
no date;
n int;
fine1 int;
diff int;
begin
	select DateofIssue into d from Borrower where roll=rollno;
	no = now();
	diff=no-d;
if (diff>15) then
	n=diff;
	if (n>30) then
		n=(n-30);
		fine1=n*50+15*5;
		update Borrower set Status='R' where roll=rollno;
		insert into Fine values(rollno,d,fine1);	
	else
		n=n-15;
		fine1=n*5;
		update Borrower set Status='R' where roll=rollno;
		insert into Fine values(rollno,d,fine1);
	end if;
	
end if;

if (diff<=15) then 
	fine1=0;
	update Borrower set Status='R' where roll=rollno;
	insert into Fine values(rollno,d,fine1); 
end if;
end
$$ language plpgsql;
	

