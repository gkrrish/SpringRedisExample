-- Lua script to save serialized data to Redis
--serialized employee object consumes less space instead of saving directly

local key = KEYS[1]   -- Key for storing the serialized data
local serialized_data = ARGV[1]   -- Serialized data to be saved

-- Save the serialized data to Redis
redis.call('SET', key, serialized_data)

return "Serialized data saved successfully"
