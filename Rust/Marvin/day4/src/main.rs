use std::fs::File;
use std::io;
use std::io::BufRead;
use std::path::Path;

#[derive(Debug)]
struct BingoBoard {
    cells: Vec<Vec<i32>>,
    marked: Vec<Vec<bool>>,
}

impl BingoBoard {
    fn new() -> Self {
        BingoBoard {
            cells: vec![],
            marked: vec![vec![false; 5]; 5],
        }
    }

    fn insert_line(&mut self, line: Vec<i32>) {
        self.cells.push(line);
    }

    fn mark(&mut self, &num: &i32) {
        for y in 0..5 {
            for x in 0..5 {
                if self.cells[y][x] == num {
                    self.marked[y][x] = true;
                }
            }
        }
    }

    fn has_won(&self) -> bool {
        let mut complete_line = false;

        // Check rows
        for y in 0..5 {
            if !complete_line {
                complete_line = self.marked[y].iter().all(|e| *e);
            }
        }

        // Check columns
        for x in 0..5 {
            if !complete_line {
                complete_line = self.marked.iter().map(|line| line[x]).all(|e| e);
            }
        }
        /*
        This wasn't needed, but I really liked my solution and wanna keep it :p
        // Check diagonals
        // -> top-left -- bottom-right
        if !complete_line {
            let mut is_marked_line = true;
            for i in 0..5 {
                if !self.marked[i][i] {
                    is_marked_line = false;
                    break;
                }
            }
            complete_line = is_marked_line;
        }

        // -> bottom-left -- top-right
        if !complete_line {
            let mut is_marked_line = true;
            for i in 0..5 {
                if !self.marked[(i-4 as i8).abs() as usize][i as usize] {
                    is_marked_line = false;
                    break;
                }
            }
            complete_line = is_marked_line;
        }
        */

        complete_line
    }

    fn unmarked_sum(&self) -> i32 {
        let mut sum = 0;
        for y in 0..5 {
            for x in 0..5 {
                if !self.marked[y][x] {
                    sum += self.cells[y][x];
                }
            }
        }

        sum
    }
}

fn parse_boards(lines: &[String]) -> Vec<BingoBoard> {
    let mut boards: Vec<BingoBoard> = Vec::new();

    let mut line_iter = lines.iter().skip(2);
    let mut line = line_iter.next();

    while line.is_some() {
        let mut line_str = line.unwrap();

        let mut board = BingoBoard::new();

        for _ in 0..5 {
            let line_parse: Vec<i32> = line_str
                .as_str()
                .split_whitespace()
                .map(|e| e.parse::<i32>().unwrap())
                .collect();
            board.insert_line(line_parse);

            if let Some(next_line) = line_iter.next() {
                line_str = next_line;
            }
        }

        boards.push(board);
        line = line_iter.next();
    }

    boards
}

fn main() {
    let lines = read_input("input.txt").unwrap();
    let lines: Vec<String> = lines.map(|e| e.unwrap()).collect();
    let draws: Vec<i32> = lines[0]
        .split(',')
        .map(|e| e.parse::<i32>().unwrap())
        .collect();

    // Step 1: Find the first that wins
    find_first(&draws, parse_boards(&lines));

    // Step 2: Find the last that wins
    find_last(&draws, parse_boards(&lines));
}

fn find_first(draws: &[i32], mut boards: Vec<BingoBoard>) {
    for draw in draws {
        for board in &mut boards {
            board.mark(draw);

            if board.has_won() {
                println!(
                    "A board won with the score {} at the draw {}",
                    board.unmarked_sum() * draw,
                    draw
                );
                return;
            }
        }
    }
}

fn find_last(draws: &[i32], mut boards: Vec<BingoBoard>) {
    for draw in draws {
        let mut boards_to_delete: Vec<usize> = Vec::new();

        for board_index in 0..boards.len() {
            boards[board_index].mark(draw);

            if boards.len() == 1 && boards[board_index].has_won() {
                println!(
                    "The last board has been found and has the score {}",
                    boards[board_index].unmarked_sum() * *draw
                );
            }
            if boards[board_index].has_won() {
                boards_to_delete.push(board_index);
            }
        }

        // If we remove them in reverse index order, none of the indexes we want to remove change
        boards_to_delete.sort_unstable();
        boards_to_delete.reverse();
        for i in boards_to_delete {
            boards.remove(i);
        }
    }
}

fn read_input<P>(filename: P) -> io::Result<io::Lines<io::BufReader<File>>>
where
    P: AsRef<Path>,
{
    let file = File::open(filename)?;
    Ok(io::BufReader::new(file).lines())
}
