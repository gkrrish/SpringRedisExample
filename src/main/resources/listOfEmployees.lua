local key = KEYS[1] 
local serializedEmployees = ARGV[1] -- Serialized list of employees

local employees = cjson.decode(serializedEmployees) -- Decode JSON to Lua table

for _, employee in ipairs(employees) do
    -- Insert each employee into Redis using suitable commands (e.g., HSET for a hash)
    local employeeKey = key .. ':' .. employee.id -- Create a unique key for each employee
    redis.call('HSET', employeeKey, 'name', employee.name)
    redis.call('HSET', employeeKey, 'age', employee.age)
    redis.call('HSET', employeeKey, 'city', employee.city)
    -- Add more fields as needed
end

return "Employees inserted successfully"
