SELECT  TOP (1000) 
  case
    -- Test for not valid positions
    when Start.Pos = 1 or Stop.Pos = 0
    then m.languages
    else substring(m.languages, Start.Pos, Stop.Pos - Start.Pos)
  end
from disciplinabd..movies m 
  cross apply (select charindex('(', m.languages)+1) as Start(Pos)
  cross apply (select charindex(')', m.languages, Start.Pos)) as Stop(Pos)
WHERE
  languages IS NOT NULL

SELECT DISTINCT TOP (1000) 
  case
    -- Test for not valid positions
    when Start.Pos = 1 or Stop.Pos = 0
    then m.languages
    else substring(m.languages, Start.Pos, Stop.Pos - Start.Pos)
  end
languages from disciplinabd..movies m 
  cross apply (select charindex('(', m.languages)+1) as Start(Pos)
  cross apply (select charindex(')', m.languages, Start.Pos)) as Stop(Pos)
WHERE
  m.languages IS NOT NULL
  
  


SELECT substring('(only a few words)', charindex('(', '(only a few words)')+1, charindex(')', '(only a few words)') - charindex('(', '(only a few words)')+8 ) FROM disciplinabd..movies m

SELECT TOP (1000) substring(m.languages, charindex('(', m.languages)+1, charindex(')', m.languages) - charindex('(', m.languages)+8 ) FROM disciplinabd..movies m WHERE m.languages IS NOT NULL


SELECT charindex(')', '(only a few words)')
