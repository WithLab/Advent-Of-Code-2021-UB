use std::f64::INFINITY;
use std::fs;

fn main() {
    let vals: Vec<i32> = fs::read_to_string("input.txt").unwrap()
        .split(",")
        .map(|e| { println!("{}", e);e.parse::<i32>().unwrap() })
        .collect();

    let min:i32 = 0;
    let max:i32 = vals.iter().max().unwrap().to_owned();

    // Step 1: Constant cost for fuel

    let mut lowest_fuel = f64::INFINITY as i32;
    let mut lowest_height = f64::INFINITY as i32;

    for height in min..=max {
        let mut used_fuel = 0_i32;
        for crab in &vals {
            used_fuel += (crab - height).abs();
        }
        if used_fuel < lowest_fuel as i32 {
            lowest_fuel = used_fuel;
            lowest_height = height;
        }
    }

    println!("{} {}", lowest_height, lowest_fuel);

    // Step 2: Increasing cost for fuel
    let mut lowest_fuel = f64::INFINITY as i32;
    let mut lowest_height = f64::INFINITY as i32;

    for height in min..=max {
        let mut used_fuel = 0_i32;
        for crab in &vals {
            let n = (crab - height).abs();
            // Use the Gaussian sum formula
            used_fuel += (n*n + n)/2 as i32
        }
        if used_fuel < lowest_fuel as i32 {
            lowest_fuel = used_fuel;
            lowest_height = height;
        }
    }

    println!("{} {}", lowest_height, lowest_fuel);
}
