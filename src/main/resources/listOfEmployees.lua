local serializedEmployees = ARGV[1] -- Serialized list of employees

local employees = cjson.decode(serializedEmployees) -- Decode JSON to Lua table

for _, employee in ipairs(employees) do
    -- Insert each employee into Redis using suitable commands (e.g., HSET for a hash)
    redis.call('HSET', 'employees:' .. employee.id, 'name', employee.name)
    redis.call('HSET', 'employees:' .. employee.id, 'age', employee.age)
    redis.call('HSET', 'employees:' .. employee.id, 'city', employee.city)
    -- Add more fields as needed
end

return "Employees inserted successfully"
