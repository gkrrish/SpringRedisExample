local key = KEYS[1]    -- Key for the hash data structure
local id = ARGV[1]     -- ID of the record
local name = ARGV[2]   -- Name of the record
local age = ARGV[3]    -- Age of the record
local city = ARGV[4]   -- City of the record
local TTL = tonumber(ARGV[5])  -- Time to live in seconds

-- Set the fields of the hash data structure
redis.call('HSET', key, 'ID', id)
redis.call('HSET', key, 'Name', name)
redis.call('HSET', key, 'Age', age)
redis.call('HSET', key, 'City', city)

-- Set the expiration time
redis.call('EXPIRE', key, TTL)

return "Record saved successfully"