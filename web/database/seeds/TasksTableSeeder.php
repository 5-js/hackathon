<?php

use Illuminate\Database\Seeder;
use Faker\Factory as Faker;
use App\Task;

class TasksTableSeeder extends Seeder
{
    /**
     * Run the database seeds.
     *
     * @return void
     */
    public function run()
    {
        foreach(range(1,50) as $index)
        {
            Task::create($this->data($index));
        }
    }

    private function data($id)
    {
        $faker = Faker::create();

        return [
            'title'    => $faker->word, 
            'content'  => $faker->realText, 
            'due_date' => $faker->dateTime, 
            'class_id' => null, 
            'user_id'  => $id
        ];
    }
}
