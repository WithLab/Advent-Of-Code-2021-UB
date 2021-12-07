use std::fs::File;
use std::io::{self, BufRead};
use std::path::Path;

const GRID_SIZE: usize = 1000;

fn main() {
    // If you know a better method of parsing things in Rust, please tell me. This is pain.
    let lines:Vec<Vec<i32>> = read_lines("./input.txt")
        .unwrap()
        .iter()
        // Map over every line
        .map(|l| {
            // Split at the arrow, trim, then split at the , and flatten
            let split_stuff:Vec<i32> = l
                .split("->")
                .map(|e| e.trim())
                .map(|e| e.split(",").collect::<Vec<&str>>())
                .flatten()
                // Parse every element into integers
                .map(|e| e.parse::<i32>().unwrap())
                .collect();
            split_stuff
        })
        .collect();

    // 1000x1000 grid for spots
    let mut grid:Vec<[i32;GRID_SIZE]>= vec![[0;GRID_SIZE];GRID_SIZE];

    // Step 1: Ignore diagonals

    // Interpolate between points in the Vec, that are [x1, y1, x2, y2]
    // Check which one is the one that changes
    for line in &lines {
        // Vertical
        if line[0] == line[2] {
            let x = line[0] as usize;
            for y in line[1].min(line[3])..=line[1].max(line[3]) {
                grid[y as usize][x] += 1;
            }
        }
        // Horizontal
        else if line[1] == line[3] {
            let y = line[1] as usize;
            for x in line[0].min(line[2])..=line[0].max(line[2]) {
                grid[y][x as usize] += 1;
            }
        }
    }

    // Count cross spots
    let mut cross_amt = 0;
    for i in 0..GRID_SIZE {
        for j in 0..GRID_SIZE {
            if grid[i][j] > 1 {
                cross_amt += 1;
            }
        }
    }

    println!("{}", cross_amt);

    // Step 2: Also check diagonals

    let mut grid:Vec<[i32;GRID_SIZE]>= vec![[0;GRID_SIZE];GRID_SIZE];

    // Interpolate between points in the Vec, that are [x1, y1, x2, y2]
    // Check which one is the one that changes
    for line in &lines {
        // Vertical
        if line[0] == line[2] {
            let x = line[0] as usize;
            for y in line[1].min(line[3])..=line[1].max(line[3]) {
                grid[y as usize][x] += 1;
            }
        }
        // Horizontal
        else if line[1] == line[3] {
            let y = line[1] as usize;
            for x in line[0].min(line[2])..=line[0].max(line[2]) {
                grid[y][x as usize] += 1;
            }
        }
        // Diagonal
        else {
            // Types are annoying a range isn't the same as a reversed range.
            // So I can't just build specific iterators and go with them.
            // I gotta do it all seperately. Or at least I have not found a way to do it elegantly yet
            if line[0] < line[2] && line[1] < line[3] {
                let mut x_coords = line[0]..=line[2];
                let mut y_coords = line[1]..=line[3];
                let mut x = x_coords.next();
                let mut y = y_coords.next();
                while x.is_some() && y.is_some() {
                    grid[y.unwrap() as usize][x.unwrap() as usize] += 1;
                    x = x_coords.next();
                    y = y_coords.next();
                }
            } else if line[0] < line[2] && line[1] > line[3] {
                let mut x_coords = line[0]..=line[2];
                let mut y_coords = (line[3]..=line[1]).rev();
                let mut x = x_coords.next();
                let mut y = y_coords.next();
                while x.is_some() && y.is_some() {
                    grid[y.unwrap() as usize][x.unwrap() as usize] += 1;
                    x = x_coords.next();
                    y = y_coords.next();
                }
            } else if line[0] > line[2] && line[1] < line[3] {

                let mut x_coords = (line[2]..=line[0]).rev();
                let mut y_coords = line[1]..=line[3];
                let mut x = x_coords.next();
                let mut y = y_coords.next();
                while x.is_some() && y.is_some() {
                    grid[y.unwrap() as usize][x.unwrap() as usize] += 1;
                    x = x_coords.next();
                    y = y_coords.next();
                }
            } else {
                let mut x_coords = (line[2]..=line[0]).rev();
                let mut y_coords = (line[3]..=line[1]).rev();
                let mut x = x_coords.next();
                let mut y = y_coords.next();
                while x.is_some() && y.is_some() {
                    grid[y.unwrap() as usize][x.unwrap() as usize] += 1;
                    x = x_coords.next();
                    y = y_coords.next();
                }
            }
        }
    }

    // Count cross spots
    let mut cross_amt = 0;
    for i in 0..GRID_SIZE {
        for j in 0..GRID_SIZE {
            if grid[i][j] > 1 {
                cross_amt += 1;
            }
        }
    }

    println!("{}", cross_amt);
}

fn read_lines<P>(filename: P) -> io::Result<Vec<String>>
    where P: AsRef<Path>, {
    let file = File::open(filename)?;
    Ok(
        io::BufReader::new(file).lines()
            .map(|l| l.expect("Could not parse line"))
            .collect()
    )
}