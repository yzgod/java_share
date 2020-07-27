local num = redis.call('incr', KEYS[1])
if tonumber(num) == 1 then
	redis.call('pexpire', KEYS[1], ARGV[1])
end
return tonumber(num)