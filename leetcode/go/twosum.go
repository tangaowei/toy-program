package main
import "fmt"

func twoSum(nums[] int, target int) []int {
	var indexMap = make(map[int]int)
	for i := 0; i < len(nums); i++ {
		if v, ok := indexMap[target - nums[i]]; ok {
			return []int{v, i}
		}
		indexMap[nums[i]] = i
	}
	return []int{0,0}
}

func main() {
	nums := []int{2, 7, 11, 15}
	target := 9
	result := twoSum(nums, target)
	fmt.Printf("%d, %d",result[0], result[1])
}
