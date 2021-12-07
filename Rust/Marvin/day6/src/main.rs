use std::error::Error;
use std::fs;
use std::io::{self, BufRead};
use std::path::Path;
use std::collections::HashMap;

#[derive(Debug)]
struct Sea {
    fishies: Vec<i128>,
}

impl Sea {
    fn new(input: Vec<i8>) -> Self {
        let mut h: Vec<i128> = Vec::new();
        for i in 0..9 {
            h.push(0);
        }

        for fish in input {
            h[fish as usize] += 1;
        }

        Sea {
            fishies:h
        }
    }
    fn tick(&mut self, amt:i32) {
        for _ in 0..amt {
            let mut birthing_fishies = 0_i128;
            for i in 0..=8 {
                if i == 0 {
                    birthing_fishies = self.fishies[i];
                } else {
                    self.fishies[i-1] = self.fishies[i];
                }
            }

            self.fishies[8] = birthing_fishies;
            self.fishies[6] += birthing_fishies;
        }
    }
}

fn main() {
    let vals: Vec<i8> = fs::read_to_string("input.txt").unwrap()
        .split(",")
        .map(|e| e.parse::<i8>().unwrap())
        .collect();

    let mut sea = Sea::new(vals);
    sea.tick(80);

    println!("{:?}", sea.fishies.iter().sum::<i128>());

    sea.tick(256-80);

    println!("{:?}", sea.fishies.iter().sum::<i128>());
}


