package main
import "fmt"
import "os"

func max(a, b int) int {
	if a > b {
		return a
	} else {
		return b
	}
}

func lengthOfLongestSubstring(s string) int {
	byteMap := make(map[byte]int)
	ret := 0
	left := 0
	for i := 0; i < len(s); i++ {
		if v, ok := byteMap[s[i]]; ok && v >= left{
			ret = max(ret, i - left)
			left = v + 1
		}
		byteMap[s[i]] = i
		fmt.Println(ret, ":", left)
	}
	ret = max(ret, len(s) - left)
	return ret
}

func main() {
	argWithoutProg := os.Args[1:]
	if len(argWithoutProg) <= 0 {
		fmt.Println("Usage: go run LongestSubString xxx")
		return
	}
	str := argWithoutProg[0]
	ret := lengthOfLongestSubstring(str)
	fmt.Println(str + ": ", ret)
}
