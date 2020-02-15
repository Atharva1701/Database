create or replace function insb (roll integer,name varchar(20),DateofIssue date,NameofBook varchar(20),Status varchar(5)) returns void as $$
begin
	insert into Borrower values(roll,name,DateofIssue,NameofBook,Status);
end;

$$ language plpgsql;
