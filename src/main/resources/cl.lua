local key = KEYS[1]
local max_burst = tonumber(ARGV[1])
local nums = tonumber(ARGV[2])
local times = ARGV[3]
return redis.call('CL.THROTTLE', key, max_burst, nums, times)